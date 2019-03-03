package request;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Request {
    public static <T> T makeGetRequest(String api, Class<T> responseType){
        ResponseEntity<T> responseEntity = new RestTemplate().getForEntity(api, responseType);
        return responseEntity.getBody();
    }
}
