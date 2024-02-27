package hellojpa.jpql;

import jakarta.persistence.*;

import java.util.List;

public class JpaMainCase {
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

            String query =
                    "select " +
                            "case when m.age <= 10 then '학생요금' " +
                            "when m.age >= 60 then '학생요금' " +
                            "else '일반요금' end " +
                    "from Member m";
            List<String> result = em.createQuery(query, String.class).getResultList();

            for (String s: result) {
                System.out.println("s = " + s);
            }
            String query2 = "select coalesce(m.username, '이름없는회원') as username from Member m";
            List<String> result2 = em.createQuery(query2, String.class).getResultList();
            for (String s : result2) {
                System.out.println("s2 = " + s);
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
