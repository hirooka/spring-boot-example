package example.domain.dto;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExampleOne {
    private UUID id;
    private String name;
    private List<ExampleMany> exampleManyList;
}
