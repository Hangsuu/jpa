package hellojpa;

import jakarta.persistence.*;

@Entity
@Table(name="member3")
public class MemberOneWayMapping {
    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String username;

//    @Column(name="TEAM_ID")
//    private Long teamId;
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    // 일대다 양방향의 경우 매핑이 되어 있는 읽기 전용으로 만들어줘야됨 (다대일 양방향 사용 권장)
//    @ManyToOne
//    @JoinColumn(name="TEAM_ID", insertable = false, updatable = false)
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        // 연관관계 편의 메서드
        team.getMembers().add(this);
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
