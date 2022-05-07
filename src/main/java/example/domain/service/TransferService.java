package example.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.InputStream;
import java.util.zip.GZIPInputStream;

@Slf4j
@Service
public class TransferService implements ITransferService {
    @Override
    public ResponseEntity<StreamingResponseBody> get(String url) {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>(outputStream -> {
            final ResponseExtractor<ResponseEntity<InputStream>> responseExtractor = response -> {
              IOUtils.copy(response.getBody(), outputStream);
              return null;
            };
            restTemplate.execute(url, HttpMethod.GET, null, responseExtractor);
        }, httpHeaders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StreamingResponseBody> getByGzip(String url) {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>(outputStream -> {
            final ResponseExtractor<ResponseEntity<InputStream>> responseExtractor = response -> {
                IOUtils.copy(new GZIPInputStream(response.getBody()), outputStream);
                return null;
            };
            restTemplate.execute(url, HttpMethod.GET, null, responseExtractor);
        }, httpHeaders, HttpStatus.OK);
    }
}
