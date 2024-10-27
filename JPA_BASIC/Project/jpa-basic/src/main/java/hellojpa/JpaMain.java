package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street", "10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new Address("old1", "street2", "10000"));
            member.getAddressHistory().add(new Address("old2", "street2", "10000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("=============== start 1 ===============");
            Member findMember = em.find(Member.class, member.getId());

            System.out.println("=============== start 2 ===============");
            List<Address> addressHistory = findMember.getAddressHistory(); // 지연로딩
            for (Address address : addressHistory) {
                System.out.println("address = " + address);
            }

            Set<String> favoriteFoods = findMember.getFavoriteFoods(); // 지연로딩
            for (String favoriteFood : favoriteFoods) {
                System.out.println("favoriteFood = " + favoriteFood);
            }

            System.out.println("=============== start 3 ===============");
            // homeCity -> newCity
            // findMember.getHomeAddress().setCity("newCity"); // 불가능 -> 값타입은 불변해야한다.
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCiry", a.getStreet(), a.getZipcode())); // 새로운 인스턴스로 갈아끼워야 한다.

            // 값 타입 컬렉션 updqte
            // 치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            // old1 -> new1
//            findMember.getAddressHistory().remove(new Address("old1", "street2", "10000"));
//            findMember.getAddressHistory().add(new Address("new1", "street2", "10000"));

            findMember.getAddressHistory().remove(new AddressEntity("old1", "street2", "10000"));
            findMember.getAddressHistory().add(new AddressEntity("new1", "street2", "10000"));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void printMember(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team = " + team.getName());
    }
}
