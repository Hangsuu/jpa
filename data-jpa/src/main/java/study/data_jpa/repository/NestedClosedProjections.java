package study.data_jpa.repository;

public interface NestedClosedProjections {

    String getUserName();
    TeamInfo getTeam();

    interface TeamInfo {
        String getName();
    }
}
