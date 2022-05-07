package example.domain.service;

import example.domain.dto.ExampleOne;
import example.domain.entity.ExampleOneEntity;
import example.domain.repository.IExampleOneRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ExampleServiceTests {

    @Autowired
    private IExampleService exampleService;

    @MockBean
    private IExampleOneRepository exampleOneRepository;

    @MockBean
    private ModelMapper modelMapper;

    private static ExampleOneEntity entity;
    private static ExampleOne dto;
    private static UUID id = UUID.randomUUID();

    @BeforeAll
    static void init() {
        entity = new ExampleOneEntity();
        entity.setId(id);
        dto = new ExampleOne();
        dto.setId(id);
    }

    @Test
    void Create_WithId_Success() {
        when(modelMapper.map(dto, ExampleOneEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, ExampleOne.class)).thenReturn(dto);
        when(exampleOneRepository.save(entity)).thenReturn(entity);
        ExampleOne exampleOne = exampleService.create(dto);
        assertThat(exampleOne.getId()).isEqualTo(id);
    }

    @Test
    void Get_ById_Success() {
        when(modelMapper.map(entity, ExampleOne.class)).thenReturn(dto);
        when(exampleOneRepository.findById(id)).thenReturn(Optional.of(entity));
        ExampleOne exampleOne = exampleService.get(id);
        assertThat(exampleOne.getId()).isEqualTo(id);
    }

    @Test
    void Delete_ById_Success() {
        exampleService.delete(id);
        verify(exampleOneRepository, times(1)).deleteById(id);
    }
}
