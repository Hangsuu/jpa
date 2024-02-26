package hellojpa.collection;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Address7 {
    String city;
    String street;
    private String zipcode;

    public Address7() {
    }

    public Address7(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address7 address7 = (Address7) o;
        return Objects.equals(city, address7.city) && Objects.equals(street, address7.street) && Objects.equals(zipcode, address7.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcode);
    }
}
