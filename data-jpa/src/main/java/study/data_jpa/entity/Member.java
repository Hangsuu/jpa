package study.data_jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
    @Id @GeneratedValue
    private Long id;

    private String userName;

    protected Member() {}

    public Member(String userName) {
        this.userName = userName;
    }
}
