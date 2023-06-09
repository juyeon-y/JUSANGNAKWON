package com.osakak.jusangnakwon.domain.liquor.api;

import com.osakak.jusangnakwon.common.response.ResponseDto;
import com.osakak.jusangnakwon.domain.liquor.api.response.LiquorListMainResponse;
import com.osakak.jusangnakwon.domain.liquor.application.LiquorNotLoggedInService;
import com.osakak.jusangnakwon.domain.liquor.dto.LiquorType;
import com.osakak.jusangnakwon.domain.user.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "liquor", description = "공통 술 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/list")
public class LiquorListController {
    private final LiquorNotLoggedInService liquorNotLoggedInService;

    private LiquorListMainResponse getLiquorListWithPaging(User user, int page, LiquorType liquorType) {
        Pageable pageable = PageRequest.of(page, 12);
        return liquorNotLoggedInService.getLiquorList(user, liquorType, pageable);
    }

    /**
     * 술 전체 보기 와인
     *
     * @return 인기순(랭킹 순 조회 12개)
     */
    @GetMapping("l1/{page}")
    public ResponseEntity<ResponseDto> listWine(@AuthenticationPrincipal User user, @PathVariable int page) {
        LiquorListMainResponse liquorList = getLiquorListWithPaging(user, page, LiquorType.WINE);
        ResponseDto responseDto = ResponseDto.builder()
                .success(true)
                .body(liquorList)
                .build();
        return ResponseEntity.ok(responseDto);

    }

    /**
     * 술 전체 보기 위스키
     *
     * @return 인기순(랭킹 순 조회 12개)
     */
    @GetMapping("l2/{page}")
    public ResponseEntity<ResponseDto> listWhisky(@AuthenticationPrincipal User user, @PathVariable int page) {
        LiquorListMainResponse liquorList = getLiquorListWithPaging(user, page, LiquorType.WHISKY);
        ResponseDto responseDto = ResponseDto.builder()
                .success(true)
                .body(liquorList)
                .build();
        return ResponseEntity.ok(responseDto);

    }

    /**
     * 술 전체 보기 맥주
     *
     * @return 인기순(랭킹 순 조회 12개)
     */
    @GetMapping("l3/{page}")
    public ResponseEntity<ResponseDto> listBeer(@AuthenticationPrincipal User user, @PathVariable int page) {
        LiquorListMainResponse liquorList = getLiquorListWithPaging(user, page, LiquorType.BEER);
        ResponseDto responseDto = ResponseDto.builder()
                .success(true)
                .body(liquorList)
                .build();
        return ResponseEntity.ok(responseDto);

    }

    /**
     * 술 전체 보기 전통주
     *
     * @return 인기순(랭킹 순 조회 12개)
     */
    @GetMapping("l4/{page}")
    public ResponseEntity<ResponseDto> traditional(@AuthenticationPrincipal User user, @PathVariable int page) {
        LiquorListMainResponse liquorList = getLiquorListWithPaging(user, page, LiquorType.TRADITION);
        ResponseDto responseDto = ResponseDto.builder()
                .success(true)
                .body(liquorList)
                .build();
        return ResponseEntity.ok(responseDto);

    }

    /**
     * 술 전체 보기 칵테일
     *
     * @return 인기순(랭킹 순 조회 12개)
     */
    @GetMapping("l5/{page}")
    public ResponseEntity<ResponseDto> listCocktail(@AuthenticationPrincipal User user, @PathVariable int page) {
        LiquorListMainResponse liquorList = getLiquorListWithPaging(user, page, LiquorType.COCKTAIL);
        ResponseDto responseDto = ResponseDto.builder()
                .success(true)
                .body(liquorList)
                .build();
        return ResponseEntity.ok(responseDto);

    }

    /**
     * 술 전체 보기 홈텐더
     *
     * @return 인기순(랭킹 순 조회 12개)
     */
    @GetMapping("l6/{page}")
    public ResponseEntity<ResponseDto> listHometender(@AuthenticationPrincipal User user, @PathVariable int page) {
        LiquorListMainResponse liquorList = getLiquorListWithPaging(user, page, LiquorType.HOMETENDER);
        ResponseDto responseDto = ResponseDto.builder()
                .success(true)
                .body(liquorList)
                .build();
        return ResponseEntity.ok(responseDto);

    }
}
