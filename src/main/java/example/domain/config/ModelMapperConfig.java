package example.domain.config;

import example.domain.dto.ExampleOne;
import example.domain.entity.ExampleOneEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        PropertyMap<ExampleOneEntity, ExampleOne> propertyMapEntityToDto = new PropertyMap<ExampleOneEntity, ExampleOne>() {
            @Override
            protected void configure() {
                map(source.getExampleManyEntityList(), destination.getExampleManyList());
            }
        };
        PropertyMap<ExampleOne, ExampleOneEntity> propertyMapDtoToEntity = new PropertyMap<ExampleOne, ExampleOneEntity>() {
            @Override
            protected void configure() {
                map(source.getExampleManyList(), destination.getExampleManyEntityList());
            }
        };
        modelMapper.addMappings(propertyMapEntityToDto);
        modelMapper.addMappings(propertyMapDtoToEntity).setPostConverter(context -> {
            context.getDestination().getExampleManyEntityList().forEach(exampleManyEntity -> exampleManyEntity.setExampleOneEntity(context.getDestination()));
            return context.getDestination();
        });
        return modelMapper;
    }
}
