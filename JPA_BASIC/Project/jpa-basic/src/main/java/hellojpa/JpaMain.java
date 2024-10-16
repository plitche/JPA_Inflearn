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

            tx.commit(); // transaction을 commit하는 시점에 쿼리가 실행됨
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
