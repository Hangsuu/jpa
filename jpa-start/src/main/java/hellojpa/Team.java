package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team extends BaseEntity{
    @Id @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;

    private String name;

    // 양방향 관계에서 연관관계를 맺는 차이 설정을 위해 mappedBy 설정 중요
    @OneToMany(mappedBy = "team") // 반대편 entity의 매핑된 변수명
    // 일대다 단방향의 경우 @JoinColumn 꼭 추가!
//    @OneToMany
//    @JoinColumn(name="TEAM_ID")
    // 일대다 양방향의 경우 (다대일 양방향 사용 권장)
//    @OneToMany
//    @JoinColumn(name="TEAM_ID")
    private List<MemberOneWayMapping> members = new ArrayList<>();

    public List<MemberOneWayMapping> getMembers() {
        return members;
    }

    public void setMembers(List<MemberOneWayMapping> members) {
        this.members = members;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMember(MemberOneWayMapping member) {
        member.setTeam(this);
        members.add(member);
    }
}
