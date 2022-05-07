package example.api.v1;

import example.domain.service.ITransferService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Slf4j
@AllArgsConstructor
@RequestMapping
@RestController
public class TransferRestController {

    private final ITransferService transferService;

    @GetMapping
    ResponseEntity<StreamingResponseBody> get(@RequestParam String url) {
        return transferService.get(url);
    }
}
