package com.venture.suyaho.repository;

import com.venture.suyaho.domain.AdminBoard;
import com.venture.suyaho.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdminBoardRepository extends JpaRepository<AdminBoard, Long> {
    // 제목으로 게시글 검색
    List<AdminBoard> findByTradeTitleContainingIgnoreCase(String keyword);

    // 사용자 번호로 게시글 검색
    List<AdminBoard> findByUser_UserNo(Integer userNo);

    // 사용자 번호 리스트로 게시글 검색
    @Query("SELECT ab FROM AdminBoard ab JOIN FETCH ab.user WHERE ab.user.userNo IN :userListNum")
    List<AdminBoard> findByUser_UserNoIn(@Param("userListNum") List<Long> userListNum);

    // 최대 trade_num 값을 가져오는 메서드
    @Query("SELECT MAX(a.tradeNum) FROM AdminBoard a")
    Long findMaxTradeNum();

}