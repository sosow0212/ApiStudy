package self.study.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


//@RestController
public class withFlaskController {
    @GetMapping("/work")
    public ResponseEntity<?> work() {

        StringBuilder result = new StringBuilder();

        String urlStr = "http://localhost:7000/get";

        URL url = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br;

            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine;

            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine + "\n\r");
            }

            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }
}
