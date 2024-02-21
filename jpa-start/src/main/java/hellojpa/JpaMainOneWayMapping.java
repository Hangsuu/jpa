package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMainOneWayMapping {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code
        try {
            //저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            MemberOneWayMapping member = new MemberOneWayMapping();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            MemberOneWayMapping findMember = em.find(MemberOneWayMapping.class, member.getId());
            // 양방향 연관관계
            List<MemberOneWayMapping> members = findMember.getTeam().getMembers();

            for (MemberOneWayMapping m : members) {
                System.out.println("m = " + m.getUsername());
            }

            //db에 외래키 업데이트
//            Team newTeam = em.find(Team.class, 1L);
//            findMember.setTeam(newTeam);
            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
