package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 등록
            /*
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA"); // 여기까지가 비영속 상태
            em.persist(member); // 객체를 저장한 상태 -> 영속 상태
            */

            // 수정
            /*
            Member findMember = em.find(Member.class, 1L); // JPA를 통해서 가져오면 관리하기 시작하면서 변경된 부분이 있는지 체크한다.
            findMember.setName("Hello JPA");
            */

            // JPQL: Member 객체를 대상으로 조회(테이블x)
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(5)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }

            // 비영속
            Member member = new Member();
            member.setId(10L);
            member.setName("Jack");

            // 영속
            Member findMember = em.find(Member.class, 10L);
            Member findMember2 = em.find(Member.class, 10L); // 영속 엔티티의 동일성 보장

            System.out.println("findMember = " + (findMember == findMember2)); // true

            //==========
            //영속
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(150L, "B");

            em.persist(member1);
            em.persist(member2);
            // persist해도 쿼리가 실행되지 않고 commit 하는 시점에 쿼리들을 모아서 한번에 실행함


            //===== 엔티티 수정 변경 감지 =====
            Member member3 = em.find(Member.class, 150L);
            member3.setName("ZZZZ"); // persist 하지 않아도 변경감지(Dirty checking)하여 update쿼리가 자동으로 실행됨


            tx.commit(); // transaction을 commit하는 시점에 쿼리가 실행됨
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
