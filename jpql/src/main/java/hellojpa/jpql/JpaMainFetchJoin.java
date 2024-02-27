package hellojpa.jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMainFetchJoin {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code
        try {
            // 페치 조인과 일반 조인의 차이
            // 일반 조인은 select절에 지정한 엔티티만 조회하고, 페치조인은 연관된 엔티티까지 조회해줌(사실상 즉시로딩)
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

            String query = "select m From Member m";
            List<Member> result = em.createQuery(query, Member.class).getResultList();
            for (Member member : result) {
                System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
                //회원1, 팀A(SQL)
                //회원2, 팀B(1차캐시)
                //회원3, 팀B(SQL) => N+1 문제 발생
            }
            em.flush();
            em.clear();
            String query2 = "select m From Member m join fetch m.team";
            List<Member> result2 = em.createQuery(query2, Member.class).getResultList();
            for (Member member : result2) {
                System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
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
