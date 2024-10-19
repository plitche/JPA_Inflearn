package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
// @Table(uniqueConstraints = "")
public class Member {

    @Id
    private Long id;

    // DB와 다른 컬럼명을 쓰고 싶을 때, null 제약 조건
    @Column(name = "name", nullable = false)
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING) // Enum타입을 사용하고 싶을 때
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob // varchar를 넘어서는 큰 문자열 타입
    private String description;

    @Transient // DB와 관계없는 memory에서만 사용하고 싶을 때
    private int temp;

    public Member() {
    }
}
