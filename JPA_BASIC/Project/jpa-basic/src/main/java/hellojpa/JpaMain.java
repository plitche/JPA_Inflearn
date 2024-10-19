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
            // 영속 상태
            Member member = em.find(Member.class, 150L);
            member.setName("John");

            // 준영속 상태
            em.detach(member); // 특정 엔티티만 준영속 상태로 변환
            em.clear(); // entityManager안에 있는 영속성 컨텍스트를 전체 삭제
            em.close(); // 영속성 컨텍스트를 종료

            tx.commit(); // transaction을 commit하는 시점에 쿼리가 실행됨
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
