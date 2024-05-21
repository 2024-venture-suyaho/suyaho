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

    private static final Random RANDOM = new Random();

    public Integer generateTradeNum() {
        // 거래 번호 생성 로직을 구현합니다.
        // 예시: 랜덤한 8자리 숫자를 생성합니다.
        return (int) (RANDOM.nextDouble() * 90000000 + 10000000);
    }

    @Transactional
    public void saveTrade(Trade trade) {
        tradeRepository.save(trade);
    }

    public List<Trade> getTradeListByCategory(String categoryName) {
        return tradeRepository.findByCategoryName(categoryName);
    }

    public List<Trade> getTradeList() {
        return tradeRepository.findAll();
    }

    public Trade findTradeByNum(Long tradeNum) {
        return tradeRepository.findById(tradeNum).orElse(null);
    }
}
