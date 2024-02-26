package hellojpa.collection;

import hellojpa.embeded.Address;
import hellojpa.embeded.BaseEntity;
import hellojpa.embeded.Period;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="member7")
public class Member7 {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String username;

    @Embedded
    private Address7 homeAddress;

    @ElementCollection
    @CollectionTable(name="FAVORITE_FOOD", joinColumns =
        @JoinColumn(name="MEMBER_ID")
    )
    @Column(name="FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name="ADDRESS7", joinColumns =
        @JoinColumn(name="MEMBER_ID")
    )
    private List<Address7> addressHistory = new ArrayList<>();

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

    public Address7 getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address7 homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<Address7> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<Address7> addressHistory) {
        this.addressHistory = addressHistory;
    }
}
