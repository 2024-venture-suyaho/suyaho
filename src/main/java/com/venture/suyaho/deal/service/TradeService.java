package com.venture.suyaho.deal.service;

import com.venture.suyaho.deal.entity.Category;
import com.venture.suyaho.deal.entity.Trade;
import com.venture.suyaho.deal.repository.TradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradeService implements TradeServiceInterface {

    private final TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public List<Trade> getTradeList() {
        return tradeRepository.findAll();
    }

    @Override
    public List<Trade> getTradeListByCategory(Category category) {
        return tradeRepository.findAll().stream()
                .filter(trade -> trade.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public void writeTrade(Trade trade) {
        tradeRepository.save(trade);
    }

    public void saveTrade(Trade trade) {
        tradeRepository.save(trade);
    }
}

interface TradeServiceInterface {
    List<Trade> getTradeList();
    List<Trade> getTradeListByCategory(Category category);
    void writeTrade(Trade trade);
}