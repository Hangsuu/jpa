package study.data_jpa.repository;

public class UserNameDto {

    private final String userName;

    public UserNameDto(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
