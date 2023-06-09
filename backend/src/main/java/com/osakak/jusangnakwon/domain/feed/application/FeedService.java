package com.osakak.jusangnakwon.domain.feed.application;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.osakak.jusangnakwon.common.errors.FeedNotFoundException;
import com.osakak.jusangnakwon.common.errors.UserNotFoundException;
import com.osakak.jusangnakwon.domain.feed.api.response.FeedListResponse;
import com.osakak.jusangnakwon.domain.feed.dao.CommentRepository;
import com.osakak.jusangnakwon.domain.feed.dao.FeedRepository;
import com.osakak.jusangnakwon.domain.feed.dao.LikeRepository;
import com.osakak.jusangnakwon.domain.feed.dao.RatingRepository;
import com.osakak.jusangnakwon.domain.feed.dto.*;
import com.osakak.jusangnakwon.domain.feed.entity.Comment;
import com.osakak.jusangnakwon.domain.feed.entity.Feed;
import com.osakak.jusangnakwon.domain.feed.entity.Like;
import com.osakak.jusangnakwon.domain.feed.entity.Rating;
import com.osakak.jusangnakwon.domain.feed.mapper.FeedDtoMapper;
import com.osakak.jusangnakwon.domain.feed.mapper.FeedMapper;
import com.osakak.jusangnakwon.domain.liquor.dto.LiquorType;
import com.osakak.jusangnakwon.domain.user.dao.UserRepository;
import com.osakak.jusangnakwon.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FeedService {

    private final FeedRepository feedRepository;
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final FeedDtoMapper feedDtoMapper = Mappers.getMapper(FeedDtoMapper.class);
    private final FeedMapper feedMapper = Mappers.getMapper(FeedMapper.class);
    static int pageNumber = 0;
    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;
    private final Storage storage;

    @Transactional
    public FeedDto createFeed(Long id, FeedDto feedDto, RatingDto ratingDto, MultipartFile image) throws IOException {
        User user = findUser(id);
        String uuid = null;
        if (image!=null && !image.isEmpty()) {
            uuid = UUID.randomUUID().toString();
            String ext = image.getContentType();
            BlobInfo blobInfo = storage.create(
                    BlobInfo.newBuilder(bucketName, uuid)
                            .setContentType(ext)
                            .build(),
                    image.getInputStream()
            );
            uuid = "https://storage.googleapis.com/" + bucketName + "/" + uuid;
        }
        feedDto.setImg(uuid);
        Feed feed = feedMapper.feedDtoToFeed(feedDto, user);
        Double ratingScore = feedDto.getRatingScore();
        feed = feedRepository.save(feed);
        if (FeedType.리뷰글.equals(feed.getType())) {
            Rating rating = feedMapper.ratingDtoToRating(ratingDto, user);
            ratingRepository.save(rating);
            feed.setRating(rating);
            updateRatingAvgOfLiquor(rating.getLiquorType(), rating.getLiquorId());
        }
        feedDto = feedMapper.feedToFeedDto(feed);
        feedDto.setLikeCnt(Long.parseLong("0"));
        feedDto.setLiked(false);
        feedDto.setRatingScore(ratingScore);
        return feedDto;
    }

    private void updateRatingAvgOfLiquor(LiquorType liquorType, Long liquorId) {
        switch (liquorType) {
            case BEER:
                feedRepository.updateBeerRatingAvgByLiquorId(liquorId);
                break;
            case COCKTAIL:
                feedRepository.updateCocktailRatingAvgByLiquorId(liquorId);
                break;
            case HOMETENDER:
                feedRepository.updateHometenderRatingAvgByLiquorId(liquorId);
                break;
            case TRADITION:
                feedRepository.updateTraditionRatingAvgByLiquorId(liquorId);
                break;
            case WINE:
                feedRepository.updateWineRatingAvgByLiquorId(liquorId);
                break;
            case WHISKY:
                feedRepository.updateWhiskyRatingAvgByLiquorId(liquorId);
        }
    }

    public FeedListResponse getFeedList(Long id, Pageable pageable) {
        User user = findUser(id);
        Page<FeedListDto> feedPage = feedRepository.findFeedPageWithLike(user.getId(), pageable);
        return getFeedListResponse(feedPage.getTotalPages(), feedPage.getPageable(), feedPage.getContent());
    }

    public FeedListResponse getFeedListByType(Long id, String type, Pageable pageable) {
        User user = findUser(id);
        Page<FeedListDto> feedPage = feedRepository.findFeedPageWithLikeByType(user.getId(), FeedType.findFeedType(type), pageable);
        return getFeedListResponse(feedPage.getTotalPages(), feedPage.getPageable(), feedPage.getContent());
    }

    public FeedDto getFeedDetail(Long id, Long feedId) {
        User user = findUser(id);
        FeedDto feedDto = feedRepository.findFeedWithRatingAndLike(user.getId(), feedId);
        List<CommentDto> comments = feedRepository.findCommentListByFeedId(feedId);
        feedDto.setComments(comments);
        return feedDto;
    }

    @Transactional
    public List<CommentDto> createComment(Long id, CommentDto commentDto) {
        User user = findUser(id);
        Long feedId = commentDto.getFeedId();
        Feed feed = findFeed(feedId);
        Comment comment = feedMapper.commentDtoToComment(commentDto, user, feed);
        commentRepository.save(comment);
        return feedRepository.findCommentListByFeedId(feedId);
    }

    @Transactional
    public void updateLike(Long id, Long feedId, Boolean isLiked) {
        User user = findUser(id);
        Feed feed = findFeed(feedId);
        likeRepository.findByUserIdAndFeedId(id, feedId)
                .ifPresentOrElse(like -> like.setLiked(isLiked), () -> {
                    Like like = Like.builder().user(user).feed(feed).isLiked(isLiked).build();
                    likeRepository.save(like);
                });
    }

    private FeedListResponse getFeedListResponse(int totalPage, Pageable pageable, List<FeedListDto> list) {
        pageNumber = pageable.getPageNumber();
        return feedDtoMapper.toFeedListResponse(list, totalPage, pageNumber);
    }

    private User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    private Feed findFeed(Long id) {
        return feedRepository.findById(id).orElseThrow(() -> new FeedNotFoundException(id));
    }

}