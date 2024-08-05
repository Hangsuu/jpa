package study.data_jpa.repository;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import study.data_jpa.entity.Member;
import study.data_jpa.entity.Team;

public class MemberSpec {

    public static Specification<Member> teamName(final String teamName) {
        return  new Specification<Member>() {

            @Override
            public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                if (StringUtils.isEmpty(teamName)) {
                    return null;
                }

                Join<Member, Team> t = root.join("team", JoinType.INNER.INNER);// 회원과 조인
                return criteriaBuilder.equal(t.get("name"), teamName);
            }
        };
    }

    public static Specification<Member> userName(final String userName) {
        return (Specification<Member>) (root, query, builder) ->
            builder.equal(root.get("userName"), userName);
    }
}
