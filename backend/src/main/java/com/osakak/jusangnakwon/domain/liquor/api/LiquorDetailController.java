package com.osakak.jusangnakwon.domain.liquor.api;

import com.osakak.jusangnakwon.common.response.ResponseDto;
import com.osakak.jusangnakwon.domain.liquor.api.response.LiquorDetailResponse;
import com.osakak.jusangnakwon.domain.liquor.application.LiquorDetailService;
import com.osakak.jusangnakwon.domain.liquor.dto.LiquorType;
import com.osakak.jusangnakwon.domain.user.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "liquorDetail", description = "술 상세 페이지")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/detail")
public class LiquorDetailController {
    private final LiquorDetailService liquorDetailService;

    /**
     * 와인 상세 페이지
     *
     * @param id 술 아이디
     * @return 술 정보
     */
    @GetMapping("l1/{id}")
    @Tag(name = "liquorDetail")
    public ResponseEntity<ResponseDto> wineDetail(@PathVariable Long id, @AuthenticationPrincipal User user) {
        LiquorDetailResponse liquorDetail = liquorDetailService.getLiquorDetail(user, LiquorType.WINE, id);
        return ResponseEntity.ok(ResponseDto.builder()
                .body(liquorDetail)
                .success(true)
                .build());
    }

    /**
     * 위스키 상세 페이지
     *
     * @param id 술 아이디
     * @return 술 정보
     */
    @GetMapping("l2/{id}")
    @Tag(name = "liquorDetail")
    public ResponseEntity<ResponseDto> whiskyDetail(@PathVariable Long id, @AuthenticationPrincipal User user) {
        LiquorDetailResponse liquorDetail = liquorDetailService.getLiquorDetail(user, LiquorType.WHISKY, id);
        return ResponseEntity.ok(ResponseDto.builder()
                .body(liquorDetail)
                .success(true)
                .build());
    }

    /**
     * 맥주 상세 페이지
     *
     * @param id 술 아이디
     * @return 술 정보
     */
    @GetMapping("l3/{id}")
    @Tag(name = "liquorDetail")
    public ResponseEntity<ResponseDto> beerDetail(@PathVariable Long id, @AuthenticationPrincipal User user) {
        LiquorDetailResponse liquorDetail = liquorDetailService.getLiquorDetail(user, LiquorType.BEER, id);
        return ResponseEntity.ok(ResponseDto.builder()
                .body(liquorDetail)
                .success(true)
                .build());
    }

    /**
     * 전통주 상세 페이지
     *
     * @param id 술 아이디
     * @return 술 정보
     */
    @GetMapping("l4/{id}")
    @Tag(name = "liquorDetail")
    public ResponseEntity<ResponseDto> traditionDetail(@PathVariable Long id, @AuthenticationPrincipal User user) {
        LiquorDetailResponse liquorDetail = liquorDetailService.getLiquorDetail(user, LiquorType.TRADITION, id);
        return ResponseEntity.ok(ResponseDto.builder()
                .body(liquorDetail)
                .success(true)
                .build());
    }

    /**
     * 칵테일 상세 페이지
     *
     * @param id 술 아이디
     * @return 술 정보
     */
    @GetMapping("l5/{id}")
    @Tag(name = "liquorDetail")
    public ResponseEntity<ResponseDto> cocktailDetail(@PathVariable Long id, @AuthenticationPrincipal User user) {
        LiquorDetailResponse liquorDetail = liquorDetailService.getLiquorDetail(user, LiquorType.COCKTAIL, id);
        return ResponseEntity.ok(ResponseDto.builder()
                .body(liquorDetail)
                .success(true)
                .build());
    }

    /**
     * 홈텐더 상세 페이지
     *
     * @param id 술 아이디
     * @return 술 정보
     */
    @GetMapping("l6/{id}")
    @Tag(name = "liquorDetail")
    public ResponseEntity<ResponseDto> hometenderDetail(@PathVariable Long id, @AuthenticationPrincipal User user) {
        LiquorDetailResponse liquorDetail = liquorDetailService.getLiquorDetail(user, LiquorType.HOMETENDER, id);
        return ResponseEntity.ok(ResponseDto.builder()
                .body(liquorDetail)
                .success(true)
                .build());
    }


}
