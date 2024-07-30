package study.data_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.data_jpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUserNameAndAgeGreaterThan(String userName, int age);
    // 전체 조회 (find...by, ...에 아무거나 들어가도 됨)
    List<Member> findTop3HelloBy();
}
