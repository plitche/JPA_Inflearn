package study.data_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.data_jpa.Entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 구현 코드가 없다
    // 구현체가 없는데 동작하는 이유는
    // : Spring이 Proxy객체를 만들면서 구현체를 spring jpa가 만들어서 injection을 해주기 때문

    // @Repository를 생략할 수 있다.
}
