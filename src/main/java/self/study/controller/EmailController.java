package self.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import self.study.service.EmailService;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/email") // 이메일 인증 코드 보내기
    public ResponseEntity<?> emailAuth(@RequestBody Map<String, String> email) throws Exception {
        emailService.sendSimpleMessage(email.get("email"));

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/verifyCode") // 이메일 인증 코드 검증
    public ResponseEntity<?> verifyCode(@RequestBody Map<String, String> code) {
        if(EmailService.ePw.equals(code.get("code"))) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(false, HttpStatus.BAD_GATEWAY);
        }
    }
}