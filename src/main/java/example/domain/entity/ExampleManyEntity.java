package example.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity(name = "many")
public class ExampleManyEntity extends BaseEntity {

    @Column(length = 32, nullable = false)
    private String name;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "one_id")
    private ExampleOneEntity exampleOneEntity;
}
