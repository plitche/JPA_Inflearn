package study.data_jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.data_jpa.Entity.Member;
import study.data_jpa.dto.MemberDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 구현 코드가 없다
    // 구현체가 없는데 동작하는 이유는
    // : Spring이 Proxy객체를 만들면서 구현체를 spring jpa가 만들어서 injection을 해주기 때문

    // @Repository를 생략할 수 있다.

    // 주요 메서드
    /**
     * save
     * delete
     * findById
     * getOne
     * findAll
     */

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findHelloBy();

    List<Member> findTop3HelloBy();

    @Query(name = "Member.findByUsername") // 생략 가능
    List<Member> findByUsername(@Param("username") String username);

    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new study.data_jpa.dto.MemberDTO(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDTO> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);

    // 반환 타입
    List<Member> findListByUsername(String username); // 컬렉션
    Member findMemberByUsername(String username); // 단건
    Optional<Member> findOptionalByUsername(String username); // 단건 Optional

    // 페이징
    Page<Member> findByAge(int age, Pageable pageable);
    Slice<Member> findMemberByAge(int age, Pageable pageable);

    @Query(value = "select m from Member m left join m.team t",
            countQuery = "select count(m.username) from Member m")
    Page<Member> findMember2ByAge(int age, Pageable pageable);

    // 벌크성 수정 쿼리
    @Modifying(clearAutomatically = true) // 자동으로 영속성 컨텍스트를 clear해준다.
    @Query("update Member m set m.age = m.age + 1 where m.age >= m.age")
    int bulkAgePlus(@Param("age") int age);

}
