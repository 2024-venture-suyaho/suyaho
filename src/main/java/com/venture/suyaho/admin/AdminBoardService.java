package com.venture.suyaho.admin;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminBoardService {

    @Autowired
    private AdminBoardRepository adminBoardRepository;

    @Transactional(readOnly = true)
    public List<AdminBoard> getAllAdminBoards() {
        List<AdminBoard> boards = adminBoardRepository.findAll();
        for (AdminBoard board : boards) {
            System.out.println("Trade Num: " + board.getTradeNum());
            System.out.println("User School Num: " + board.getSchoolNum());
        }
        return boards;
    }
}