package com.venture.suyaho.service;

import com.venture.suyaho.domain.Book;
import com.venture.suyaho.domain.TradeBoard;
import com.venture.suyaho.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

//    public void saveBook(int tradeNum, int userNo, char writing, char cover, char discoloration, char damage, String publisher) {
//        TradeBoard trade = new TradeBoard(); // 수정: Trade 객체 생성 및 설정
//        trade.setId((long) tradeNum);
//
//        Book book = new Book(); // 수정: Book 객체 생성 및 설정
//        book.setTrade(trade);
//        book.setWriting(writing);
//        book.setCover(cover);
//        book.setDiscoloration(discoloration);
//        book.setDamage(damage);
//        book.setCompany(publisher);
//
//        bookRepository.save(book); // 수정: bookRepository를 통해 Book 저장
//    }
}
