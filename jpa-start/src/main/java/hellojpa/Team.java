package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;

    private String name;

    // 양방향 관계에서 연관관계를 맺는 차이 설정을 위해 mappedBy 설정 중요
    @OneToMany(mappedBy = "team") // 반대편 entity의 매핑된 변수명
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
}
