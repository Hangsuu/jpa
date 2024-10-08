package study.data_jpa.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import study.data_jpa.dto.MemberDto;
import study.data_jpa.entity.Member;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom, JpaSpecificationExecutor<Member> {

    List<Member> findByUserNameAndAgeGreaterThan(String userName, int age);
    // 전체 조회 (find...by, ...에 아무거나 들어가도 됨)
    List<Member> findTop3HelloBy();

//    없어도 메서드 명으로 알아서 찾아줌, 네임드 쿼리가 없어도 메서드명으로 쿼리 만들어줌
//    @Query(name = "Member.findByUserName")
    List<Member> findByUserName(@Param("userName") String userName);

    @Query("select m from Member m where m.userName = :userName and m.age = :age")
    List<Member> findUser(@Param("userName") String userName, @Param("age") int age);

    @Query("select m.userName from Member m")
    List<String> findUserNameList();

    @Query("select new study.data_jpa.dto.MemberDto(m.id, m.userName, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.userName in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);

    List<Member> findListByUserName(String userName); // Collection
    Member findMemberByUserName(String userName); // 단건
    Optional<Member> findOptionalByUserName(String userName); // Optional

    Page<Member> findByAge(int age, Pageable pageable);
    Slice<Member> findSliceByAge(int age, Pageable pageable);

    List<Member> findListByAge(int age, PageRequest pageRequest);

    // 성능 향상을 위해 count 쿼리 분리(join 등 최적화)
    @Query(value="select m from Member m left join m.team t where m.age = :age",
            countQuery = "select count(m) from Member m")
    Page<Member> findQueryByAge(@Param("age") int age, PageRequest pageRequest);

    @Modifying(clearAutomatically = true) // update query에서 넣어줘야됨
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    @EntityGraph(attributePaths = ("team"))
    List<Member> findEntityGraphByUserName(@Param("userName") String userName);

    @EntityGraph("Member.all")
    List<Member> findNamedEntityGraphByUserName(@Param("userName") String userName);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUserName(String userName);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUserName(String userName);

//    List<UserNameOnly> findProjectionsByUserName(@Param("userName") String userName);

    List<UserNameDto> findProjectionsByUserName(@Param("userName") String userName);
    <T> List<T> findProjectionsByUserName(@Param("userName") String userName, Class<T> type);

    // 네이티브 Sql을  DTO로 조회할 때는 JdbcTemplate이나 MyBatis 사용 권장
    @Query(value = "select * from member where user_name = ?", nativeQuery = true)
    Member findByNativeQuery(String userName);

    @Query(value = "select m.member_id as id, m.user_name, t.name as teamName " +
            "from member m left join team t",
            countQuery = "select count(*) from member",
            nativeQuery = true)
    Page<MemberProjection> findByNativeProjection(Pageable pageable);
}
