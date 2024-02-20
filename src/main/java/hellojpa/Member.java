package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//@Table(name="member")
public class Member {

    @Id
    private Long id;
//    @Column(name="name")
    // DDL 생성기능은 JPA 실행 로직에는 영향을 주지 않고 DDL 자동 생성될 때에만 사용됨
    @Column(unique = true, length = 10)
    private String name;

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
