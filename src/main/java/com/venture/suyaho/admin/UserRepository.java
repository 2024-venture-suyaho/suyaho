package com.venture.suyaho.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    User findByUserSchoolNum(Integer userSchoolNum);
    // 학번의 일부를 포함하는 사용자 검색
    @Query("SELECT u FROM User u WHERE str(u.userSchoolNum) LIKE %:userSchoolNum%")
    List<User> findByUserSchoolNumContaining(@Param("userSchoolNum") String userSchoolNum);

}