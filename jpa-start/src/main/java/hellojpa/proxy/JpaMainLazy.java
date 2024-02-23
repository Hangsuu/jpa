package hellojpa.proxy;

import hellojpa.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMainLazy {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code
        try {

            Team5 team = new Team5();
            team.setName("teamA");
            em.persist(team);

            Member5 member = new Member5();
            member.setUsername("member1");
            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            Member5 m = em.find(Member5.class, member.getId());
            System.out.println("m = " + m.getTeam().getClass());

            System.out.println("=========================");
            m.getTeam().getName();

            // 실무에서는 가급적 지연로딩 사용, fetch join으로 해결 가능
            // @ManyToOne, @OneToOne은 default가 EAGER로 되어 있어 LAZY로 직접 변환 필요
            List<Member5> members = em.createQuery("select m from Member5 m join fetch m.team", Member5.class)
                            .getResultList();

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
