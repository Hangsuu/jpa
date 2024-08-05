package study.data_jpa.repository;

import org.springframework.beans.factory.annotation.Value;

public interface UserNameOnly {
    // Open Projection
    // spl을 쓰면 데이터를 일단 다 Entity로 다 가져오고 가져온걸로 원하는 데이터를 찍어준다
//    @Value("#{target.userName + ' ' + target.age}")
    String getUserName();
}
