package jpql;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("userA");
            member.setAge(10);
            em.persist(member);

            TypedQuery<Member> typeQuery = em.createQuery("select m from Member m", Member.class); // 반환 타입 확실
            Query query = em.createQuery("select m.username, m.age from Member m"); // 반환 타입 불분명

            List<Member> resultList = typeQuery.getResultList();
            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
            }

            TypedQuery<Member> parameterQuery
                    = em.createQuery("select m from Member m where m.username = :username", Member.class);

            parameterQuery.setParameter("username", "userA");
            Member singleResult = parameterQuery.getSingleResult();
            System.out.println("singleResult = " + singleResult);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
