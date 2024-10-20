package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
// @SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")
// @Table(uniqueConstraints = "")
public class Member {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
     @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에 insert가 되어야만 id를 알 수 있음
    private Long id;

    // DB와 다른 컬럼명을 쓰고 싶을 때, null 제약 조건
    @Column(name = "name", nullable = false)
    private String username;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
