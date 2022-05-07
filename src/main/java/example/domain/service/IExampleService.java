package example.domain.service;

import example.domain.dto.ExampleOne;
import java.util.Map;
import java.util.UUID;

public interface IExampleService {

    ExampleOne create(ExampleOne exampleOne);

    ExampleOne get(UUID id);

    ExampleOne update(ExampleOne exampleOne);

    ExampleOne update(Map<String, Object> updates);

    void delete(UUID id);
}
