//package com.venture.suyaho.service;
//
//import com.venture.suyaho.domain.AdminBoard;
//import com.venture.suyaho.repository.AdminBoardRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class PostBoardService {
//
//    @Autowired
//    private AdminBoardRepository adminBoardRepository;
//
//    // 모든 게시물 조회
//    public List<AdminBoard> getAllTradeBoards() {
//        return adminBoardRepository.findAll();
//    }
//
//    // 게시물 작성
//    public void createTradeBoard(AdminBoard adminBoard) {
//        adminBoardRepository.save(adminBoard);
//    }
//
//
//    public void updateTradeBoard(Long id, AdminBoard adminBoard) {
//        AdminBoard existingAdminBoard = adminBoardRepository.findById(id).orElse(null);
//        if (existingAdminBoard != null) {
//            existingAdminBoard.setTradeTitle(adminBoard.getTradeTitle());
//            existingAdminBoard.setTradeText(adminBoard.getTradeText());
//            // 다른 필드들도 업데이트 필요
//            adminBoardRepository.save(existingAdminBoard);
//        }
//    }
//
//    // 게시물 삭제
//    public void deleteTradeBoard(Long id) {
//        adminBoardRepository.deleteById(id);
//    }
//
//    // 게시물 상세 조회
//    public AdminBoard getTradeBoardById(Long id) {
//        return adminBoardRepository.findById(id).orElse(null);
//    }
//}
