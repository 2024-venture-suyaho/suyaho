package com.venture.suyaho.deal.service;

import com.venture.suyaho.deal.dto.TradeRequest;
import com.venture.suyaho.deal.entity.Book;
import com.venture.suyaho.deal.entity.Category;
import com.venture.suyaho.deal.entity.Trade;
import com.venture.suyaho.deal.repository.BookRepository;
import com.venture.suyaho.deal.repository.CategoryRepository;
import com.venture.suyaho.deal.repository.TradeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Transactional
    public void saveTradeAndBookData(Trade trade, Book book) {
        // 거래 테이블에 데이터 저장
        Trade savedTradeBoard = tradeRepository.save(trade);

        // 책 테이블에 거래 번호 설정 후 데이터 저장
        book.setTrade(savedTradeBoard);
        bookRepository.save(book);
    }
    public List<Trade> getTradeListByCategory(String categoryName) {
        return tradeRepository.findByCategoryName(categoryName);
    }

    private static final Random RANDOM = new Random();

    public Integer generateTradeNum() {
        // 거래 번호 생성 로직을 구현합니다.
        // 예시: 랜덤한 8자리 숫자를 생성합니다.
        return (int) (RANDOM.nextDouble() * 90000000 + 10000000);
    }


    public Trade saveTrade(TradeRequest tradeRequest) {
        Trade trade = new Trade();
        trade.setTitle(tradeRequest.getTitle());
        trade.setProduct(tradeRequest.getProductName());
        trade.setQuantity(tradeRequest.getQuantity());
        trade.setPrice(tradeRequest.getPrice());
        trade.setDetail(tradeRequest.getDescription());
        trade.setTradeComplete(false);

        Book book = new Book();
        book.setWriting(tradeRequest.isBookWriting());
        book.setCover(tradeRequest.getBookCover());
        book.setDiscoloration(tradeRequest.isBookDiscoloration());
        book.setDamage(tradeRequest.isBookDamage());
        book.setCompany(tradeRequest.getPublisher());
        book.setTrade(trade);

        trade.setBook(book);

        // Category 설정
        Category category = categoryRepository.findById(tradeRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        trade.setCategory(category);

        // 이미지 처리
        MultipartFile imageFile = tradeRequest.getImage();
        if (imageFile != null && !imageFile.isEmpty()) {
            trade.setImage(imageFile); // MultipartFile 객체를 직접 설정
        }
        tradeRepository.saveTradeWithQuery(
                trade.getTitle(),
                trade.getBook().isWriting(),
                trade.getBook().getCover(),
                trade.getBook().isDiscoloration(),
                trade.getBook().isDamage(),
                tradeRequest.getPublisher(),
                trade.getProduct(),
                trade.getQuantity(),
                trade.getPrice(),
                trade.getDetail(),
                trade.getCategory().getId(),

        );

        return tradeRepository.save(trade);
    }

    public List<Trade> getTradeList() {
        return tradeRepository.findAll();
    }

    public Trade findTradeByNum(Long tradeNum) {
        return tradeRepository.findById(tradeNum).orElse(null);
    }
}
