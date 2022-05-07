package example.domain.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity(name = "one")
public class ExampleOneEntity extends BaseEntity {

    @Column(length = 32, nullable = false)
    private String name;

    @OneToMany(mappedBy = "exampleOneEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ExampleManyEntity> exampleManyEntityList;
}
