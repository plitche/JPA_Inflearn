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
            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            em.persist(member3);

            Member member4 = new Member();
            member4.setUsername("회원4");
            em.persist(member4);

            em.flush();
            em.clear();

            String query = "select m from Member m join fetch m.team";
            String query1 = "select t from Team t join fetch t.members";

            List<Member> resultList = em.createQuery(query, Member.class)
                    .getResultList();

            for (Member m : resultList) {
                System.out.println("member = " + m.getUsername() + " " + m.getTeam().getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
