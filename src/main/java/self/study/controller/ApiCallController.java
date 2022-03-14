package self.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@RestController
public class ApiCallController {

    @GetMapping("/api/call")
    public String callApi() throws IOException {
        StringBuilder result = new StringBuilder();

        String urlStr = "http://openapi.data.go.kr/openapi/service/rest/Covid19?" +
                "serviceKey=tBkzXQd%2B2RB27qoAqdUFljY3OTqy2mLbKv65OQ%2BZ7bG4wT9lzN1iGCk8AHpWzEp%2BJlbR2xHJxrvO02ZBNP4oXg%3D%3D" +
                "&pageNo=0" +
                "&numOfRows=10" +
                "&type=json";

        URL url = new URL(urlStr);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

        String returnLine;

        while((returnLine = br.readLine()) != null) {
            result.append(returnLine+"\n\r");
        }

        urlConnection.disconnect();

        return result.toString();
    }
}
