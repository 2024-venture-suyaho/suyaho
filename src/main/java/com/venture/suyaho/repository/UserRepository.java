package com.venture.suyaho.repository;

import com.venture.suyaho.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmailAndUserPwd(String userEmail, String userPwd);
    List<User> findByUserNameContainingIgnoreCase(String userName);
    boolean existsByUserEmail(String userEmail);

    // 학과로 사용자 검색
    List<User> findByUserMajorContainingIgnoreCase(String userMajor);

    User findByUserSchoolNum(Integer userSchoolNum);
    // 학번의 일부를 포함하는 사용자 검색
    @Query("SELECT u FROM User u WHERE str(u.userSchoolNum) LIKE %:userSchoolNum%")
    List<User> findByUserSchoolNumContaining(@Param("userSchoolNum") String userSchoolNum);

    User findByUserName(String userName);
}
