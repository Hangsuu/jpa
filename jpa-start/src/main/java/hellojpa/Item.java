package hellojpa;

import jakarta.persistence.*;

@Entity
// default(단일테이블) = SINGLE_TABLE / 개별 테이블 : TABLE_PER_ClASS
@Inheritance(strategy = InheritanceType.JOINED)
// Dtype 칼럼을 생성해서 테이블 이름이 들어가게 됨(name=""으로 컬럼명 설정 가능)
@DiscriminatorColumn
public class Item {
    @Id @GeneratedValue
    private Long id;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private String name;
    private int price;
}
