package self.study.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL) // null 값인 필드는 JSON 응답에 포함되지 않음
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 스태틱 메소드를 이용하여 인스턴스를 생성하므로, 생성자 접근 제어 레벨은 private로 설정
@Getter
public class Response {
    private boolean success;
    private int code;
    private Result result;

    public static Response success() { // 4
        return new Response(true, 0, null);
    }

    public static <T> Response success(T data) { // 5
        return new Response(true, 0, new Success<>(data));
    }

    public static Response failure(int code, String msg) { // 6
        return new Response(false, code, new Failure(msg));
    }
}