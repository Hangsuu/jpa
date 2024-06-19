package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {
    private String cty ;
    private String street;
    private String zipcode;

    // Setter를 사용하지 않을 경우 생성자로 해결
    // 임베디드 타입 기본 생성자는 JPA 스펙 상 public 또는 protected로 설정해야됨
    protected Address() {
    }

    public Address(String cty, String street, String zipcode) {
        this.cty = cty;
        this.street = street;
        this.zipcode = zipcode;
    }
}
