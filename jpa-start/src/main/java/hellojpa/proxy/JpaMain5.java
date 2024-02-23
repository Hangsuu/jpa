package hellojpa.proxy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain5 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code
        try {
            Member5 member1 = new Member5();
            member1.setUsername("member1");
            em.persist(member1);
            Member5 member2 = new Member5();
            member2.setUsername("member2");
            em.persist(member2);

            em.flush();
            em.clear();
            Member5 m1 = em.find(Member5.class, member1.getId());
//            Member5 m2 = em.find(Member5.class, member2.getId());
            Member5 reference = em.getReference(Member5.class, member2.getId());

            // 프록시 객체는 원본 엔티티를 상속받으므로 == 대신 instance of 사용해야됨
            // true
//            System.out.println("m1 == m2 : " + (m1.getClass() == m2.getClass()));
            // false
            System.out.println("m1 == reference : " + (m1.getClass() == reference.getClass()));
            System.out.println(reference instanceof Member5);

            // 영속성 엔티티가 이미 있으면 getReference를 호출해도 엔티티 호출함
//            System.out.println("m2 = " + m2.getClass());

//            System.out.println("reference = " + reference.getClass());

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void printMember(Member5 member5) {
        String username = member5.getUsername();
        System.out.println("username = " + username);
    }

    private static void printMemberAndTeam(Member5 member5) {
        String username = member5.getUsername();
        System.out.println("username = " + username);

        Team5 team5 = member5.getTeam();
        System.out.println("team = " + team5);

    }
}
