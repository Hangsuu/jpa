package hellojpa.proxy;

import hellojpa.Locker;
import jakarta.persistence.*;

@Entity
@Table(name="member5")
public class Member5 extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TEAM_ID")
    private Team5 team;

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

    public Team5 getTeam() {
        return team;
    }

    public void setTeam(Team5 team) {
        this.team = team;
    }
}
