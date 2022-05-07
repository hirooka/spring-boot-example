package example.domain.service;

import example.domain.dto.ExampleOne;
import example.domain.entity.ExampleOneEntity;
import example.domain.repository.IExampleOneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class ExampleService implements IExampleService {

    private final IExampleOneRepository exampleOneRepository;
    private final ModelMapper modelMapper;

    @Override
    public ExampleOne create(ExampleOne exampleOne) {
        if (ObjectUtils.isEmpty(exampleOne.getId())) {
            exampleOne.setId(UUID.randomUUID());
        }
        return modelMapper.map(exampleOneRepository.save(modelMapper.map(exampleOne, ExampleOneEntity.class)), ExampleOne.class);
    }

    @Override
    public ExampleOne get(UUID id) {
        return modelMapper.map(
                exampleOneRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException("Failed to get id = " + id)
                ),
                ExampleOne.class
        );
    }

    @Override
    public ExampleOne update(ExampleOne exampleOne) {
        return null;
    }

    @Override
    public ExampleOne update(Map<String, Object> updates) {
        return null;
    }

    @Override
    public void delete(UUID id) {
        exampleOneRepository.deleteById(id);
    }
}
