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

            String query =
                    "select " +
                            "case when m.age <= 10 then '학생요금' " +
                            "     when m.age >= 60 then '경로요금' " +
                            "     else '일반요금' " +
                            "end as 요금구분 " +
                    "from Member m";

            String query1 = "select coalesce(m.username, '이름 없는 회원') from Member m";

            String query2 = "select nullif(m.username, '관리자') from Member m";


            List<String> resultList = em.createQuery(query, String.class)
                    .getResultList();

            List<String> resultList1 = em.createQuery(query1, String.class)
                    .getResultList();

            List<String> resultList2 = em.createQuery(query2, String.class)
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
