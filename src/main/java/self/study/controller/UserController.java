package self.study.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import self.study.dto.RegisterDto;
import self.study.entity.User;
import self.study.response.Response;
import self.study.service.UserService;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "전체 회원 보기", notes = "전체 회원을 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/auth")
    public Response<?> find() {
        return new Response<>("true", "조회 성공", userService.findAll());
    }

    @ApiOperation(value = "회원가입", notes="회원가입 진행")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/auth")
    public Response<?> register(@RequestBody RegisterDto registerDto) {
        return new Response<>("true", "가입 성공", userService.register(registerDto));
    }
}
