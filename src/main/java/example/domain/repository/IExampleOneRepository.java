package example.domain.repository;

import example.domain.entity.ExampleOneEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExampleOneRepository extends JpaRepository<ExampleOneEntity, UUID> {
}
