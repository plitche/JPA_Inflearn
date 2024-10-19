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
            // 플러시 : 영속성 컨텍스트의 변경 내용을 데이터베이스에 반영
            /**
             * 플러시 발생
             * - 변경 감지
             * - 수정된 엔티티 쓰기 지연 SQL 저장소에 등록
             * - 쓰기 지연 SQL 저장소의 쿼리를 데이터베이스에 전송
             *
             * 플러시 하는 방법
             * - em.flush()
             * - 트랜잭션 커밋
             * - JPQL 쿼리 실행
             */

            Member member = new Member(200L, "member200");
            em.persist(member);

            em.flush();
            // flush는 영속성 컨텍스트를 비우지 않음
            // 트랜잭션이라는 작업 단위가 중요 - 커밋 직전에만 동기화하면 됨

            tx.commit(); // transaction을 commit하는 시점에 쿼리가 실행됨
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
