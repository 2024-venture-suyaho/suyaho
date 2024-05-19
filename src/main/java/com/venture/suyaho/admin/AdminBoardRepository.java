package com.venture.suyaho.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdminBoardRepository extends JpaRepository<AdminBoard, Long> {
    List<AdminBoard> findByTradeTitleContainingIgnoreCase(String keyword);
    List<AdminBoard> findByUser_UserSchoolNum(Integer userSchoolNum);

    // 사용자 관련 메서드 추가
    List<AdminBoard> findByUser(User user);
}
