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
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("userA");
            member.setAge(10);
            member.setType(MemberType.ADMIN);
            em.persist(member);

            member.changeTeam(team);

            String query = "select 'a' || 'b' from Member m";
            String query1 = "select substring(m.username, 2, 3) from Member m";
            String query2 = "select locate('de', 'abcdefg') from Member m";

            String query3 = "select size(t.members) from Team t";

//            @OrderColumn
//            String query4 = "select index(t.members) from Team t";
            String query5 = "select function('group_concat' m.username) From Member m";

            List<String> resultList = em.createQuery(query, String.class)
                    .getResultList();


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
