package hellojpa.proxy;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team5")
public class Team5 extends BaseEntity {
    @Id @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team") // 반대편 entity의 매핑된 변수명
    private List<Member5> member5s = new ArrayList<>();

    public List<Member5> getMembers() {
        return member5s;
    }

    public void setMembers(List<Member5> member5s) {
        this.member5s = member5s;
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

    public void addMember(Member5 member5) {
        member5.setTeam(this);
        member5s.add(member5);
    }
}
