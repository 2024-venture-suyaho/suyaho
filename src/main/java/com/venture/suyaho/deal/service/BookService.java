package com.venture.suyaho.deal.service;

import com.venture.suyaho.deal.entity.Book;
import com.venture.suyaho.deal.entity.Trade;
import com.venture.suyaho.deal.repository.BookRepository;
import com.venture.suyaho.deal.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void saveBook(int tradeNum, int userNo, char writing, char cover, char discoloration, char damage, String publisher) {
        Trade trade = new Trade(); // 수정: Trade 객체 생성 및 설정
        trade.setId((long) tradeNum);

        Book book = new Book(); // 수정: Book 객체 생성 및 설정
        book.setTrade(trade);
        book.setWriting(writing);
        book.setCover(cover);
        book.setDiscoloration(discoloration);
        book.setDamage(damage);
        book.setCompany(publisher);

        bookRepository.save(book); // 수정: bookRepository를 통해 Book 저장
    }
}
