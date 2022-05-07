package example.domain.repository;

import example.domain.entity.ExampleOneEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ExampleOneRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IExampleOneRepository exampleOneRepository;

    @Test
    void Get_FindById_Success() {
        UUID id = UUID.randomUUID();
        ExampleOneEntity record = new ExampleOneEntity();
        record.setId(id);
        record.setName("name");
        entityManager.persist(record);
        ExampleOneEntity entity = exampleOneRepository.findById(id).orElseThrow();
        assertThat(entity.getId()).isEqualTo(entity.getId());
    }
}
