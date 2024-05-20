package com.venture.suyaho.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdminBoardRepository extends JpaRepository<AdminBoard, Long> {
    // 제목으로 게시글 검색
    List<AdminBoard> findByTradeTitleContainingIgnoreCase(String keyword);

    // 사용자 학번으로 게시글 검색
    List<AdminBoard> findByUser_UserSchoolNum(Integer userSchoolNum);

    // 사용자 번호로 게시글 검색
    List<AdminBoard> findByUser_UserNo(Integer userNo);

    // 사용자 관련 메서드 추가
    @Query("SELECT ab FROM AdminBoard ab WHERE ab.user.userNo IN :userNos")
    List<AdminBoard> findByUser_UserNoIn(@Param("userNos") List<Integer> userNos);

    List<AdminBoard> findByUser(User user);
}