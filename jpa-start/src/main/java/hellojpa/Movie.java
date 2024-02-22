package hellojpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
// Dtype에 들어갈 이름 설정 가능
@DiscriminatorValue("A")
public class Movie extends Item {
    public String getDirecotr() {
        return direcotr;
    }

    public void setDirecotr(String direcotr) {
        this.direcotr = direcotr;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    private String direcotr;
    private String actor;

}
