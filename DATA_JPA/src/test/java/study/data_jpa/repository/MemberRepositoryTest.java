package study.data_jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import study.data_jpa.Entity.Member;
import study.data_jpa.Entity.Team;
import study.data_jpa.dto.MemberDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
// @Rollback(value = false)
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired TeamRepository teamRepository;
    @PersistenceContext EntityManager em;

    @Test
    public void testMember() {
        Member member = new Member("userA");
        Member savedMember = memberRepository.save(member);

        Optional<Member> byId = memberRepository.findById(savedMember.getId());
        Member findMember = byId.get();

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        // 단건 조회 검증
        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        // 리스트 조회 검증
        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        memberRepository.delete(member1);
        memberRepository.delete(member2);

        long deletedCount = memberRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }

    @Test
    public void findByUsernameAndAgeGreaterThen() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);
        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(15);
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    public void namedQuery() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsername("AAA");
        assertThat(result.get(0)).isEqualTo(m1);
    }

    @Test
    public void testQuery() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findUser("AAA", 10);
        assertThat(result.get(0)).isEqualTo(m1);
    }

    @Test
    public void findUsernameList() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<String> result = memberRepository.findUsernameList();
        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void findMemberDto() {
        Member m1 = new Member("AAA", 10);
        memberRepository.save(m1);

        Team team = new Team("TeamA");
        memberRepository.save(m1);
        m1.setTeam(team);

        List<MemberDTO> result = memberRepository.findMemberDto();
        for (MemberDTO memberDTO : result) {
            System.out.println("memberDTO = " + memberDTO);
        }
    }

    @Test
    public void findByNames() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));
        for (Member m : result) {
            System.out.println("s = " + m);
        }
    }

    @Test
    public void returnType() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> aaa = memberRepository.findListByUsername("AAA"); // 없을때 빈 컬렉션 반환
        Member member = memberRepository.findMemberByUsername("AAA"); // 없을때 NULL 반환

        // 없을 때 : Optional.empty
        // 결과가 2개 이상일 때 : exception
        Optional<Member> optionalMember = memberRepository.findOptionalByUsername("AAA");
    }

    @Test
    public void paging() {
        // GIVEN
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

        // when
        Page<Member> page = memberRepository.findByAge(age, pageRequest);
        // dto로 변환 방법
        Page<MemberDTO> toMap = page.map(m -> new MemberDTO(m.getId(), m.getUsername(), null));

        // then
        // Page
        List<Member> content = page.getContent();
        long totalElements = page.getTotalElements();

        for (Member member : content) {
            System.out.println("member = " + member);
        }
        System.out.println("totalElements = " + totalElements);

        assertThat(content.size()).isEqualTo(3); // 조회된 데이터 개수
        assertThat(page.getTotalElements()).isEqualTo(5); // 전체 데이터 개수
        assertThat(page.getNumber()).isEqualTo(0); //
        assertThat(page.getTotalPages()).isEqualTo(2); // 전체 페이지 개수
        assertThat(page.isFirst()).isTrue(); // 첫번째 페이지인지 유무
        assertThat(page.hasNext()).isTrue(); // 다음 페이지가 있는지 유무

        // Slice
        Slice<Member> slice = memberRepository.findByAge(age, pageRequest);

        List<Member> sliceContent = slice.getContent();
        assertThat(sliceContent.size()).isEqualTo(3); // 조회된 데이터 개수
//        assertThat(slice.getTotalElements()).isEqualTo(5); // 전체 데이터 개수
        assertThat(slice.getNumber()).isEqualTo(0); //
//        assertThat(slice.getTotalPages()).isEqualTo(2); // 전체 페이지 개수
        assertThat(slice.isFirst()).isTrue(); // 첫번째 페이지인지 유무
        assertThat(slice.hasNext()).isTrue(); // 다음 페이지가 있는지 유무
    }

    @Test
    public void bulkUpdate() {
        // given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 19));
        memberRepository.save(new Member("member3", 20));
        memberRepository.save(new Member("member4", 11));
        memberRepository.save(new Member("member5", 40));

        // when
        int resultCount = memberRepository.bulkAgePlus(20);
        // 영속성 컨텍스트에 남아있는 상태이기 때문에 원하는 결과가나오지 않는다.
        // 때문에 영속성 컨텍스트를 비워준다.
        em.flush();
        em.clear();

        List<Member> result = memberRepository.findByUsername("member5");
        Member member = result.get(0);
        System.out.println("member = " + member);

        // then
        assertThat(resultCount).isEqualTo(3);
    }

    @Test
    public void findMemberLzy() {
        // given
        // member1 -> teamA
        // member2 -> teamB

        Team teamA = new Team("TeamA");
        Team teamB = new Team("TeamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamB);
        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();

        // when
        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            System.out.println("member = " + member.getUsername());
            System.out.println("member.team = " + member.getTeam().getName());
            // 위 team을 조회하는 시점에 1+n 문제가 발생한다.
        }

        // when2
        List<Member> findMembers = memberRepository.findMemberFetchJoin();
        for (Member findMember : findMembers) {
            System.out.println("findMember = " + findMember);
            System.out.println("findMember.team = " + findMember.getTeam().getName());
        }

        // when3
        List<Member> allMembers = memberRepository.findAll();
        for (Member allMember : allMembers) {
            System.out.println("allMember = " + allMember);
            System.out.println("allMember = " + allMember.getTeam().getName());
        }

        // when4
        List<Member> result = memberRepository.findEntityGraphByUsername("member1");
        for (Member member : result) {
            System.out.println("member = " + member);
            System.out.println("member = " + member.getTeam().getName());
        }

    }

    @Test
    public void queryHint() {
        // given
        Member member = memberRepository.save(new Member("member1", 10));
        em.flush();
        em.clear();

        // when
//        Member findMember = memberRepository.findById(member.getId()).get();
        Member findMember = memberRepository.findReadOnlyByUsername("member1");
        // 읽기 전용으로 인식하고 변겸감지 체크를 하지 않는다.
        findMember.setUsername("member2");

        em.flush();
    }

    @Test
    public void lock() {
        // given
        Member member = memberRepository.save(new Member("member1", 10));
        em.flush();
        em.clear();

        // when
        List<Member> findMember = memberRepository.findLockByUsername("member1");
        for (Member member1 : findMember) {
            System.out.println("member1 = " + member1);
        }
    }
}