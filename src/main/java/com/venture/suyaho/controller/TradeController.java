package com.venture.suyaho.controller;

import com.venture.suyaho.dto.TradeDTO;
import com.venture.suyaho.dto.TradeRequest;
import com.venture.suyaho.dto.TradeResponse;
import com.venture.suyaho.domain.Trade;
import com.venture.suyaho.repository.TradeRepository;
import com.venture.suyaho.service.BookService; // BookService 추가
import com.venture.suyaho.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
//@RequestMapping("/trade")
public class TradeController {

    private final TradeService tradeService;
    private final BookService bookService; // BookService 추가
    private final TradeRepository tradeRepository;

    @Autowired
    public TradeController(TradeService tradeService, BookService bookService,TradeRepository tradeRepository) {
        this.tradeService = tradeService;
        this.bookService = bookService;
        this.tradeRepository = tradeRepository;
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        tradeService.saveTradeImage(file);
        return "redirect:/success";
    }

    @GetMapping("/category/{categoryName}")
    public String tradeListByCategory(@PathVariable String categoryName, Model model) {
        List<Trade> tradeList = tradeService.getTradeListByCategory(categoryName);
        model.addAttribute("tradeList", tradeList);
        return "trade/list";
    }

    @GetMapping("/trade/write")
    public String writeForm(Model model) {
        model.addAttribute("tradeRequest", new TradeRequest());
        return "trade/write";
    }

    @PostMapping("/trade/write")
    public String writeTrade(@ModelAttribute TradeRequest tradeRequest) {
        System.out.println("TradeRequest: " + tradeRequest);
        // 거래 번호 생성
        int tradeNum = tradeService.generateTradeNum();

        // Trade 객체 생성 및 설정
        Trade trade = new Trade(); // 수정: Trade 객체 생성
        trade.setTitle(tradeRequest.getTitle());
        trade.setProduct(tradeRequest.getProductName());
        trade.setQuantity(tradeRequest.getQuantity());
        trade.setPrice(tradeRequest.getPrice());
        trade.setDetail(tradeRequest.getDescription());
        trade.setTradeComplete(false);

// 이미지 파일 처리
        MultipartFile imageFile = tradeRequest.getImage();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                byte[] imageBytes = imageFile.getBytes();
                trade.setImageFile(imageFile); // MultipartFile 객체 설정 (수정)
                trade.setImageData(imageBytes); // 이미지 데이터 설정 (수정)
            } catch (IOException e) {
                // 이미지 처리 중 오류 발생 시 예외 처리
                throw new RuntimeException("Failed to process image", e);
            }
        }


        // Trade 정보 저장
        tradeService.saveTrade(trade); // 수정: Trade 객체를 저장

        // 카테고리가 도서일 경우에만 Book 정보 저장
        if (tradeRequest.getCategoryId() == 1L) {
            bookService.saveBook(tradeNum, tradeRequest.getUserNo(),
                    tradeRequest.getBookWriting(),
                    tradeRequest.getBookCover(),
                    tradeRequest.getBookDiscoloration(),
                    tradeRequest.getBookDamage(),
                    tradeRequest.getPublisher()); // 수정: bookService를 통해 Book 저장
        }

        return "redirect:/trade/list";
    }

    @GetMapping("/list")
    public String tradeList(Model model) {
        List<Trade> tradeList = tradeService.getTradeList();
        model.addAttribute("tradeList", tradeList);
        return "/trade/list";
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
    @GetMapping("/trades")
    public String listTrades(Model model) {
        // 트레이드 목록을 가져와서 모델에 추가하는 로직
        List<Trade> trades = tradeService.getAllTrades();
        // 쿼리가 안찍히면 흠... 잠만요
        model.addAttribute("trades", trades);
        return "trade/list"; // trades.html 템플릿을 반환
    }
}
