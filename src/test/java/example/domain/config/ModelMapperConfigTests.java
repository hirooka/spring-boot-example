package example.domain.config;

import example.domain.dto.ExampleMany;
import example.domain.dto.ExampleOne;
import example.domain.entity.ExampleManyEntity;
import example.domain.entity.ExampleOneEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ModelMapperConfigTests {

    @Autowired
    private ModelMapperConfig modelMapperConfig;

    private static ExampleOneEntity exampleOneEntity;
    private static ExampleOne exampleOne;
    private static UUID id;

    @BeforeAll
    static void init() {
        id = UUID.randomUUID();

        exampleOneEntity = new ExampleOneEntity();
        exampleOneEntity.setId(id);
        final List<ExampleManyEntity> exampleManyEntityList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ExampleManyEntity exampleManyEntity = new ExampleManyEntity();
            exampleManyEntity.setId(UUID.randomUUID());
            exampleManyEntity.setExampleOneEntity(exampleOneEntity);
            exampleManyEntityList.add(exampleManyEntity);
        }
        exampleOneEntity.setExampleManyEntityList(exampleManyEntityList);

        exampleOne = new ExampleOne();
        exampleOne.setId(id);
        final List<ExampleMany> exampleManyList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ExampleMany exampleMany = new ExampleMany();
            exampleMany.setId(UUID.randomUUID());
            exampleManyList.add(exampleMany);
        }
        exampleOne.setExampleManyList(exampleManyList);
    }

    @Test
    void Map_EntityToDto_Success() {
        ExampleOne exampleOne = modelMapperConfig.modelMapper().map(exampleOneEntity, ExampleOne.class);
        assertThat(exampleOne.getId()).isEqualTo(id);
        assertThat(exampleOne.getExampleManyList().size()).isEqualTo(2);
    }

    @Test
    void Map_DtoToEntity_Success() {
        ExampleOneEntity exampleOneEntity = modelMapperConfig.modelMapper().map(exampleOne, ExampleOneEntity.class);
        assertThat(exampleOneEntity.getId()).isEqualTo(id);
        assertThat(exampleOneEntity.getExampleManyEntityList().size()).isEqualTo(2);
    }
}
