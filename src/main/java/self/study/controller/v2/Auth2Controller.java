package self.study.controller.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import self.study.entity.v2.SignUpDto;
import self.study.entity.v2.User2;
import self.study.handler.Response;
import self.study.service.v2.Auth2Service;

@RequiredArgsConstructor
@RestController
public class Auth2Controller {
    private final Auth2Service authService;

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.CREATED)
    public Response signUp(@RequestBody User2 user2) {
        authService.singup(user2);
        return Response.success();
    }

}
