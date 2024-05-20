package com.venture.suyaho.deal.controller;
import com.venture.suyaho.deal.entity.Category;
import com.venture.suyaho.deal.entity.Trade;
import com.venture.suyaho.deal.entity.User;
import com.venture.suyaho.deal.service.TradeService;
import com.venture.suyaho.deal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String tradeList(Model model) {
        List<Trade> tradeList = tradeService.getTradeList();
        model.addAttribute("tradeList", tradeList);
        return "trade/list";
    }

    @GetMapping("/category/{category}")
    public String tradeListByCategory(@PathVariable Category category, Model model) {
        List<Trade> tradeList = tradeService.getTradeListByCategory(category);
        model.addAttribute("tradeList", tradeList);
        return "trade/list";
    }

    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("trade", new Trade());
        return "trade/write";
    }



    @PostMapping("/trade/write")
    public String writeTrade(@ModelAttribute Trade trade, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "trade/write";
        }

        tradeService.saveTrade(trade);
        return "redirect:/trade";
    }

}