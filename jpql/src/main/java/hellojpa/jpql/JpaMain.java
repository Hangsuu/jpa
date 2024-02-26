package hellojpa.jpql;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code
        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            // TypeQuery : 반환 타입이 명확할 때
            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
            // Query : 반환 타입이 명확하지 않을 때
            Query query2 = em.createQuery("select m.username, m.age from Member m");

            // 결과 조회
            List<Member> resultList = query.getResultList();
            // 하나가 아니면 Exception 터짐
            // Member singleResult = query.getSingleResult();

            // 파라미터
            TypedQuery<Member> query3 = em.createQuery("select m from Member m where m.username = :username", Member.class);
            query3.setParameter("username", "member1");
            Member singleResult = query.getSingleResult();
            System.out.println("singResult = " + singleResult   );

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
