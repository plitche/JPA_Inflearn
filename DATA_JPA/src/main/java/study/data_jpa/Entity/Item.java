package study.data_jpa.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
//@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Item implements Persistable<Long> {

//    @Id @GeneratedValue
//    private Long id;

    @Id
    private Long id;

    @CreatedDate
    private LocalDateTime createdDate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return createdDate == null; // null 이면 새로운 객체, 아니면 있는 객체
    }
}
