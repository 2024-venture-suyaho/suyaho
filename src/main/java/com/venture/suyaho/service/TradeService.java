package com.venture.suyaho.service;
import com.venture.suyaho.domain.AdminBoard;

import com.venture.suyaho.domain.Book;
import com.venture.suyaho.repository.AdminBoardRepository;

import com.venture.suyaho.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 @Service
 public class TradeService {
     @Autowired
     private AdminBoardRepository adminBoardRepository;

     @Autowired
     private BookRepository bookRepository;

     @Transactional
     public void createTrade(AdminBoard adminBoard, Book book) {
         adminBoardRepository.save(adminBoard);
         book.setTradeNum(adminBoard.getTradeNum());  // adminBoard가 저장된 후 생성된 tradeNum을 설정
         bookRepository.save(book);
     }
}
