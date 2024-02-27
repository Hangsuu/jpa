package hellojpa.jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMainFunction {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code
        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setType(MemberType.ADMIN);
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            String query = "select concat('a', 'b') From Member m";
            List<String> result = em.createQuery(query, String.class).getResultList();
            // 묵시적 조인은 사용하지 말고 명시적 조인을 사용해라(쿼리튜닝 힘듬)
            // select t.members.username from Team t => 실패(collection은 더이상 호출 불가)
            // select m.username from Team t join t.members m => 성공

            for (String s : result) {
                System.out.println("s = " + s);
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
