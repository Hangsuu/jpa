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
            // 연관관계 편의 메서드를 이용해서 양방향 객체 모두 변경
            member.changeTeam(team);
            // 둘 중 하나 사용
//            team.addMember(member);
            em.persist(member);

            // 양뱡향 객체 모두 바꾸지 않고 flush, clear를 진행하지 않으면 밑에 find 할 경우 member의 정보가 조회되지 않는다
//            em.flush();
//            em.clear();

            MemberOneWayMapping findMember = em.find(MemberOneWayMapping.class, member.getId());
            // 양방향 연관관계
            List<MemberOneWayMapping> members = findMember.getTeam().getMembers();

            System.out.println("=================");
            for (MemberOneWayMapping m : members) {
                System.out.println("m = " + m.getUsername());
            }
            System.out.println("=================");

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
