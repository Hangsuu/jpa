package hellojpa.embeded;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="member6")
public class Member6 extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String username;

    // 기간 Period
    @Embedded
    private Period workPeriod;

    // 주소 Address
    @Embedded
    private Address homeAddress;

    // 같은이름 override
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city", column = @Column(name="WORK_CITY")),
            @AttributeOverride(name="street", column = @Column(name="WORK_street")),
            @AttributeOverride(name="zipcode", column = @Column(name="WORK_zipcode"))
    })
    private Address workAddress;

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

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
}
