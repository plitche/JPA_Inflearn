package jpql;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

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

            // 엔티티
            List<Member> result = em.createQuery("select m from Member m", Member.class)
                    .getResultList();

            // 엔티티
            List<Team> result1 = em.createQuery("select m.team from Member m", Team.class)
                    .getResultList();

            // 엔티티
            List<Team> result2 = em.createQuery("select m.team from Member m join m.team t", Team.class)
                    .getResultList();

            // 임베디드
            List<Address> result3 = em.createQuery("select o.address from Order o", Address.class)
                    .getResultList();

            // 스칼라
            List objectList = em.createQuery("select distinct m.username, m.age from Member m")
                    .getResultList(); // Object

            List<Objects[]> objectArrayList = em.createQuery("select distinct m.username, m.age from Member m")
                    .getResultList(); // Object

            List<MemberDTO> dtos = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList(); // Dto

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
