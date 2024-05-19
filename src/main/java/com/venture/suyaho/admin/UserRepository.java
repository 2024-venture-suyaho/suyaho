package com.venture.suyaho.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 사용자 이름으로 사용자 찾기
    List<User> findByUserNameContainingIgnoreCase(String userName);

    // 사용자 이메일로 사용자 찾기
    User findByUserEmail(String userEmail);

    // 사용자 전화번호로 사용자 찾기
    List<User> findByUserPhone(String userPhone);

    // 학과로 사용자 검색
    List<User> findByUserMajorContainingIgnoreCase(String userMajor);

    // 학번으로 사용자 검색
    User findByUserSchoolNum(Integer userSchoolNum);
}
