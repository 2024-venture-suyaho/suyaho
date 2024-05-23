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
     public AdminBoard saveAdminBoard(AdminBoard adminBoard) {
         return adminBoardRepository.save(adminBoard);
     }

     @Transactional
     public Book saveBook(Book book) {
         return bookRepository.save(book);
     }
}
