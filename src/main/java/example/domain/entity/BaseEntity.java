package example.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.Instant;
import java.util.UUID;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    private UUID id;

    @Version
    private long version;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedBy
    private String lastModifiedByy;

    @LastModifiedDate
    private Instant lastModifiedDate;
}
