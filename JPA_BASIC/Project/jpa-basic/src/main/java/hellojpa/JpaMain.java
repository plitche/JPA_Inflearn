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
            // 팀 저장
            Team team = new Team();
            team.setName("TeamA");;
            em.persist(team);

            // 회원 저장
            Member member = new Member();
            member.setUsername("member1");
            // 연관관계의 주인에만 값을 세팅하면 된다? X -> 양방향이면 둘다 해야한다.
            member.changeTeam(team);
            em.persist(member);

            // 연관관계의 주인이 아니기 때문에 할 필요가 없다? X -> 양방향이면 둘다 해야한다.
            // -> Member의 setTeam 편의 메소드를 통해 필요 없게 처리
            // team.getMembers().add(member);

            // team.addMember(member);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
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
