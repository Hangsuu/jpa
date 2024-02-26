package hellojpa.collection;

import hellojpa.embeded.Address;
import hellojpa.embeded.Member6;
import hellojpa.embeded.Period;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Set;

public class JpaMain7 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code
        try {

            Member7 member = new Member7();
            member.setUsername("member1");
            member.setHomeAddress(new Address7("homeCity", "street", "111111"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("족발");

            member.getAddressHistory().add(new Address7("old1", "street", "111111"));
            member.getAddressHistory().add(new Address7("old2", "street", "111111"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("===================START==============");
            Member7 findMember = em.find(Member7.class, member.getId());

            List<Address7> addressHistory = findMember.getAddressHistory();
            for (Address7 address7 : addressHistory) {
                System.out.println("address = " + address7.getCity());
            }

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for (String favoriteFood : favoriteFoods) {
                System.out.println("favoriteFood = " + favoriteFood);
            }

            //homeCity -> newCity
            // findMember.getHomeAddress().setCity("newCity"); // 이렇게 쓰면 안됨
            Address7 oldAddress = findMember.getHomeAddress();

            // 치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            findMember.getAddressHistory().remove(new Address7("old1", "street", "111111"));
            findMember.getAddressHistory().add(new Address7("newCity1", "street", "111111"));
            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
