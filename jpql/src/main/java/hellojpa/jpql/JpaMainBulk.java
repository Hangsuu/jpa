package hellojpa.jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMainBulk {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code
        try {
            // 벌크 연산은 영속성 컨텍스트를 무시하고 데이터베이스에 직접 쿼리
            // 벌크 연산을 먼저 수행 후 영속성 컨텍스트 초기화 권장
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setAge(0);
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setAge(0);
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setAge(0);
            member3.setTeam(teamB);
            em.persist(member3);

//            em.flush();
//            em.clear();
            // flush 자동 호출 (commit, query 나갈 때 강제 호출)
            int resultCount = em.createQuery("update Member m set m.age=20")
                    .executeUpdate();

            System.out.println("resultCount = " + resultCount);

            // 영속성 컨텍스트에는 flush만 되고 clear가 되지 않아 20살이 반영이 안돼있다
            System.out.println("member1.getAge = " + member1.getAge());
            System.out.println("member2.getAge = " + member2.getAge());
            System.out.println("member3.getAge = " + member3.getAge());
            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
