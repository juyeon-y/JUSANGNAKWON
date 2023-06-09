package com.osakak.jusangnakwon.domain.user.api;

import com.osakak.jusangnakwon.common.response.ResponseDto;
import com.osakak.jusangnakwon.domain.user.api.request.SurveyRequest;
import com.osakak.jusangnakwon.domain.user.api.response.GetUserResponse;
import com.osakak.jusangnakwon.domain.user.application.SurveyService;
import com.osakak.jusangnakwon.domain.user.application.UserService;
import com.osakak.jusangnakwon.domain.user.entity.User;
import com.osakak.jusangnakwon.domain.user.mapper.UserDtoMapper;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SurveyService surveyService;
    private final UserDtoMapper userDtoMapper = Mappers.getMapper(UserDtoMapper.class);

    /**
     * 유저정보 조회
     *
     * @param user
     * @return 유저정보
     */
    @ApiOperation(
            value = "유저 정보 조회",
            notes = "토큰값을 이용해 유저를 찾아서 세부정보를 리턴"
    )
    @GetMapping("/info")
    public ResponseEntity<ResponseDto> getUserInfo(@AuthenticationPrincipal User user) {
        GetUserResponse getUserResponse = userDtoMapper.toUserResponse(userService.getUserInfo(user.getUserId()));
        ResponseDto responseDto = ResponseDto.builder()
                .success(true)
                .body(getUserResponse)
                .build();
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 유저 취향 설문 결과 저장
     *
     * @param user
     * @param requestSurvey
     * @return
     */
    @ApiOperation(
            value = "유저 취향 설문 결과 저장",
            notes = "유저 취향 설문 결과 저장"
    )
    @PostMapping("/survey")
    public ResponseEntity<ResponseDto> postSurvey(@AuthenticationPrincipal User user, @RequestBody SurveyRequest requestSurvey) {
        surveyService.saveSurvey(user, requestSurvey);
        ResponseDto responseDto = ResponseDto.builder()
                .success(true)
                .build();
        return ResponseEntity.ok(responseDto);
    }
}
