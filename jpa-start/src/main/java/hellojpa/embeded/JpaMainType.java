package hellojpa.embeded;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMainType {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code
        try {
            // 값 타입과 불변객체
            // 값 타입은 불변객체로 만들어야 됨(Intger, String 등)
            Address address = new Address("city", "street", "100000");

            Member6 member1 = new Member6();
            member1.setUsername("member1");
            member1.setHomeAddress(address);
            em.persist(member1);

            // 기존의 address를 깊은복사 해서 사용해야됨
//            Address address2 = new Address(address.getCity(), address.getStreet(), address.getZipcode());

            Member6 member2 = new Member6();
            member2.setUsername("member2");
            member2.setHomeAddress(address);
            em.persist(member2);

            // member1, member2 모두 newCity로 바뀌게 됨
            member1.getHomeAddress().setCity("newCity");

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
