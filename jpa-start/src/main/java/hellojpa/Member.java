package hellojpa;

import jakarta.persistence.*;

import java.util.Date;

@Entity
//@Table(name="member")
public class Member {

    @Id
    private Long id;
    // DDL 생성기능은(unique, length 등) JPA 실행 로직에는 영향을 주지 않고 DDL 자동 생성될 때에만 사용됨
    /**
     * Column
     * name : 필드와 매핑할 테이블의 컬럼 이름 객체의 필드 이름
     * insertable,updatable : 등록, 변경 가능 여부
     * nullable(DDL) : null 값의 허용 여부를 설정한다. false로 설정하면 DDL 생성 시에 not null 제약조건이 붙는다.
     * unique(DDL) : @Table의 uniqueConstraints와 같지만 한 컬럼에 간단히 유니크 제약조건을 걸 때 사용한다.
     * columnDefinition(DDL) : 데이터베이스 컬럼 정보를 직접 줄 수 있다.
     *      ex) varchar(100) default ‘EMPTY'
     *          필드의 자바 타입과 방언 정보를 사용해서 적절한 컬럼 타입
     * length(DDL) : 문자 길이 제약조건, String 타입에만 사용한다.(255)
     * precision, scale(DDL) : BigDecimal 타입에서 사용한다(BigInteger도 사용할 수 있다).
     *      precision은 소수점을 포함한 전체 자 릿수를, scale은 소수의 자릿수다. 참고로 double, float 타입에는 적용되지 않는다.
     *      아주 큰 숫자나 정밀한 소수를 다루어야 할 때만 사용한다.
     */
    @Column(name = "name", unique = true, nullable = false, length = 10)
    private String username;

    private Integer age;

    // EnumType.Ordinal 이 default이며 사용하면 안됨(enum의 순서를 저장하겠다는 의미)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // DB는 Date, Time, TimeStamp를 구분해줘야됨
    // 최신버전 사용 시 다음과 같은 클래스로 사용해도 됨(연월 / 연월일)
    // private LocalDate localDate;
    // private LocalDateTime localDateTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // 대용량 문자열
    @Lob
    private String description;

    // DB에 연결하지 않음
    @Transient
    private int temp;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
