package hellojpa;

import hellojpa.proxy.Member5;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Locker {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToOne(mappedBy = "locker")
    private MemberOneWayMapping member;
}
