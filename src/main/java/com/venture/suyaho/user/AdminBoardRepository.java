package com.venture.suyaho.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminBoardRepository extends JpaRepository<AdminBoard, Long> {
    // 글 제목으로 찾기
    List<AdminBoard> findByTradeTitle(String tradeTitle);

}
