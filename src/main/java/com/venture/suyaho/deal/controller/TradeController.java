package com.venture.suyaho.deal.controller;

import com.venture.suyaho.deal.dto.TradeRequest;
import com.venture.suyaho.deal.dto.TradeResponse;
import com.venture.suyaho.deal.entity.Trade;
import com.venture.suyaho.deal.service.BookService;
import com.venture.suyaho.deal.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/trade")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping("/category/{categoryName}")
    public String tradeListByCategory(@PathVariable String categoryName, Model model) {
        List<Trade> tradeList = tradeService.getTradeListByCategory(categoryName);
        model.addAttribute("tradeList", tradeList);
        return "trade/list";
    }

    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("tradeRequest", new TradeRequest());
        return "trade/write";
    }
    @Autowired

    @PostMapping("/write")
    public String writeTrade(@ModelAttribute TradeRequest tradeRequest) {
        // 거래 번호 생성
        int tradeNum = tradeService.generateTradeNum();

        // 이미지 파일 처리
        MultipartFile imageFile = tradeRequest.getImage();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                byte[] imageBytes = imageFile.getBytes();
                Trade.setImage(imageBytes);
            } catch (IOException e) {
                // 이미지 처리 중 오류 발생 시 예외 처리
                throw new RuntimeException("Failed to process image", e);
            }
        }

        // Trade 정보 저장
        Trade trade = tradeService.saveTrade(tradeRequest, tradeNum);

        // 카테고리가 도서일 경우에만 Book 정보 저장
        if (tradeRequest.getCategoryId() == 1) {
            BookService.saveBook(tradeNum, tradeRequest.getUserNo(),
                    tradeRequest.getB(),
                    tradeRequest.getBookCover(),
                    tradeRequest.getBookDiscoloration(),
                    tradeRequest.get(),
                    tradeRequest.getPublisher());
        }

        return "redirect:/trade/list";
    }

    @GetMapping("/trade/list")
    public String tradeList(Model model) {
        List<Trade> tradeList = tradeService.getTradeList();
        model.addAttribute("tradeList", tradeList);
        return "trade/list";
    }

    @GetMapping("/{tradeNum}")
    public ResponseEntity<TradeResponse> getTradeDetail(@PathVariable("tradeNum") Long tradeNum) {
        Trade trade = tradeService.findTradeByNum(tradeNum);
        if (trade != null) {
            TradeResponse tradeResponse = new TradeResponse(trade);
            return ResponseEntity.ok(tradeResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
