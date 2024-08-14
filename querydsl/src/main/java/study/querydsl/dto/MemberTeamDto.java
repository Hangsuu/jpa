package study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberTeamDto {
    private Long memberId;
    private String userName;
    private int age;
    private Long teamId;
    private String teamName;

    // DTO가 순수해야 되지만 QueryDsl에 의존해야 된다는 문제가 생김
    @QueryProjection
    public MemberTeamDto(Long memberId, String userName, int age, Long teamId, String teamName) {
        this.memberId = memberId;
        this.userName = userName;
        this.age = age;
        this.teamId = teamId;
        this.teamName = teamName;
    }
}
