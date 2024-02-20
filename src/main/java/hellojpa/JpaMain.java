package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code
        try {
            // 입력
            // 비영속
            Member member = new Member();
            member.setId(101L);
            member.setUsername("HelloJPA");
            member.setRoleType(RoleType.USER);
            // 영속(영속 상태가 돼도 바로 DB에 쿼리가 날아가지 않는다
            em.persist(member);

            // 수정
//            Member findmember = em.find(Member.class, 1L);
//            findmember.setName("HelloJPA");

            // 목록 전체 조회
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(7)
//                    .getResultList();
//            for (Member member : result) {
//                System.out.println("member.name = " + member.getName());
//            }
            // commit 상태에 db에 쿼리가 날아감
            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
