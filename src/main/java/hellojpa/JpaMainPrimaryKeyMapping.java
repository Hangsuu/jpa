package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMainPrimaryKeyMapping {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code
        try {
            // 입력
            MemberPrimaryKeyMapping member1 = new MemberPrimaryKeyMapping();
            member1.setUsername("A");
            MemberPrimaryKeyMapping member2 = new MemberPrimaryKeyMapping();
            member2.setUsername("B");
            MemberPrimaryKeyMapping member3 = new MemberPrimaryKeyMapping();
            member3.setUsername("C");

            System.out.println("=====================");
            em.persist(member1);    //1, 51
            em.persist(member2);    //MEM
            em.persist(member3);    //MEM
            System.out.println("member.id = " + member1.getId());
            System.out.println("member.id = " + member2.getId());
            System.out.println("member.id = " + member3.getId());
            System.out.println("=====================");
            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
