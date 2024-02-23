package hellojpa.proxy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMainCascade {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code
        try {
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
            // cascade 설정하면 parent만 저장해도 다 저장됨
//            em.persist(child1);
//            em.persist(child2);

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
