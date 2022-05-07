package example.domain.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface ITransferService {

    ResponseEntity<StreamingResponseBody> get(String url);

    ResponseEntity<StreamingResponseBody> getByGzip(String url);
}
