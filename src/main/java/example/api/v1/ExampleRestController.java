package example.api.v1;

import example.domain.dto.ExampleOne;
import example.domain.service.IExampleService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RequestMapping("v1/examples")
@RestController
public class ExampleRestController {

    private final IExampleService exampleService;

    @PostMapping
    ExampleOne create(@RequestBody ExampleOne exampleOne) {
        return exampleService.create(exampleOne);
    }

    @GetMapping("{id}")
    ExampleOne get(@PathVariable("id") UUID id) {
        return exampleService.get(id);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        exampleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
