package com.venture.suyaho.deal.service;

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
        return (int) (RANDOM.nextDouble() * 90000000 + 10000000);
    }

    @Transactional
    public void saveTrade(Trade trade) {
        tradeRepository.save(trade);
    }

    public void saveTradeImage(MultipartFile file) throws IOException {
        Trade trade = new Trade();
        trade.setImageData(file.getBytes());
        tradeRepository.save(trade);
    }

    @Transactional
    public void saveTradeWithImageData(String title, Character bookWriting, Character bookCover, Character bookDiscoloration,
                                       Character bookDamage, String author, String publisher, String productName,
                                       int quantity, double price, String description, Long categoryId, byte[] imageData) {
        tradeRepository.saveTradeWithQuery(title, bookWriting, bookCover, bookDiscoloration, bookDamage, author,
                publisher, productName, quantity, price, description, categoryId, imageData);
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
