package hellojpa.jpql;

import jakarta.persistence.*;

import java.util.List;

public class JpaMainPaging {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code
        try {
            for(int i=0; i<100; i++ ) {
                Member member = new Member();
                member.setUsername("member" + i);
                member.setAge(100-i);
                em.persist(member);
            }

            em.flush();
            em.clear();

            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();
            for (Member member1 : result) {
                System.out.println("member1 = " + member1.toString());
            }

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
