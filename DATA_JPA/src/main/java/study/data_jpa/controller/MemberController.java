package study.data_jpa.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.data_jpa.Entity.Member;
import study.data_jpa.dto.MemberDTO;
import study.data_jpa.repository.MemberRepository;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    // 도메인 클래스 컨버터
    // http 요청은 회원 'id'를 받지만 도메인 클래스 컨버터가 중간에 동작해서 엔티티 객체를 반환
    // 주의! 해당 엔티티는 단순 조회용으로만 사용! 트랜잭션 없는 범위에서 조회했음으로
    @GetMapping("/members2/{id}")
    public String findMember2(@PathVariable("id") Member member) {
        return member.getUsername();
    }

    // page : 페이지 숫자(기본 20개)
    // size : 한 페이지 노출 개수
    // sort=id,desc : 정렬(id,내림차순)
    @GetMapping("/members")
    public Page<MemberDTO> list(@PageableDefault(size = 5, sort = "username") Pageable pageable) { // 이 설정이 application.yml 설정보다 우선한다.
        /**
         * - 접두사
         * 페이징 정보가 둘 이상이면 접두사로 구분
         * @Qualifier에 접두사명 추가
         * 예제 : /members?member_page=0&order_page=1
         */
        Page<Member> page = memberRepository.findAll(pageable);
        // return page;

        // Page 내용을 DTO로 변환하기
        Page<MemberDTO> map = page.map(member -> new MemberDTO(member.getId(), member.getUsername(), null));
        Page<MemberDTO> map2 = page.map(member -> new MemberDTO(member));
        Page<MemberDTO> map3 = page.map(MemberDTO::new);
        return map3;
    }

    @PostConstruct
    public void init() {
        for (int i=0; i<100; i++) {
            memberRepository.save(new Member("user" + i, i));
        }
    }

}
