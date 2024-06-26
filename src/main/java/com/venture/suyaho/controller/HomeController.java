package com.venture.suyaho.controller;

import com.venture.suyaho.domain.AdminBoard;
import com.venture.suyaho.domain.User;
import com.venture.suyaho.repository.AdminBoardRepository;
import com.venture.suyaho.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminBoardRepository adminBoardRepository;

    @GetMapping("/")
    public String home(HttpSession session, Model model) {

        if(session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }
        return "index";
    }

    @GetMapping("/mypage")
    public String mypage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            // 사용자 정보가 세션에 없는 경우, 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }
        return "mypage/admin-userpage";
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        List<AdminBoard> posts = adminBoardRepository.findAll();
        model.addAttribute("posts", posts);
        return "trade/posts";
    }

    @GetMapping("/adminuser")
    public String adminPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // 유저가 로그인하지 않았거나 관리자 권한이 없으면 로그인 페이지로 리다이렉트
        if (user == null || user.getUserRights() != 'Y') {
            return "redirect:/login";
        }

        // 유저가 관리자 권한을 가지고 있으면, userRepository를 사용하여 모든 유저 데이터를 가져옵니다.
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users); // 사용자 목록을 모델에 추가합니다.
        return "admin/adminpage"; // 관리자 페이지를 위한 HTML 템플릿의 이름을 반환합니다.
    }

    @GetMapping("/admin")
    public String adminMainPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null || user.getUserRights() != 'Y') {
            // 유저가 로그인하지 않았거나 관리자 권한이 없으면 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }

        // 유저가 관리자 권한을 가지고 있다면, 관리자 메인 페이지를 렌더링
        return "admin/adminmain";
    }
    @GetMapping("/adminpost")
    public String adminPost(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        // 유저가 로그인하지 않았거나 관리자 권한이 없으면 로그인 페이지로 리다이렉트
        if (user == null || user.getUserRights() != 'Y') {
            return "redirect:/login";
        }

        // 유저가 관리자 권한을 가지고 있으면, trade_board 테이블에서 데이터를 가져옵니다.
        List<AdminBoard> boardList = adminBoardRepository.findAll();
        model.addAttribute("trade_board", boardList); // 모델에 tradeList를 추가합니다.
        return "admin/adminpost"; // 관리자 게시물 페이지를 렌더링
    }


    @DeleteMapping("/api/trades/{tradeNum}")
    @ResponseBody
    public ResponseEntity<String> deleteTrade(@PathVariable Long tradeNum) {
        adminBoardRepository.deleteById(tradeNum);
        return ResponseEntity.ok("Trade deleted");
    }

    @GetMapping("/write")
    public String writePage(){

        return "trade/write"; // write.html 템플릿을 반환
    }

    @PostMapping("/write")
    public String saveTrade(HttpSession httpSession,
                            @RequestParam String tradeCategory,
                            @RequestParam String tradeTitle,
                            @RequestParam String tradeProduct,
                            @RequestParam int tradeQuantity,
                            @RequestParam int tradePrice,
                            @RequestParam String tradeText,
                            @RequestParam String tradeCondition,
                            @RequestParam char tradeComplete,
                            @RequestParam("tradePhoto") MultipartFile tradePhoto) throws IOException {
        AdminBoard adminBoard = new AdminBoard();

        adminBoard.setTradeCategory(tradeCategory);
        adminBoard.setTradeTitle(tradeTitle);
        adminBoard.setTradeProduct(tradeProduct);
        adminBoard.setTradeQuantity(tradeQuantity);
        adminBoard.setTradePrice(tradePrice);
        adminBoard.setTradeText(tradeText);
        adminBoard.setTradeCondition(tradeCondition);
        adminBoard.setTradeComplete(tradeComplete);
        adminBoard.setTradeTime(LocalDateTime.now());

        // Set the photo
        if (!tradePhoto.isEmpty()) {
            adminBoard.setTradePhoto(tradePhoto.getBytes());
        }

        // Assume that the user is already set in the session, replace this with your actual user retrieval logic
        User user = (User) httpSession.getAttribute("user");
        adminBoard.setUser(user);

        adminBoardRepository.save(adminBoard);

        return "redirect:/list"; // 저장 후 목록 페이지로 리디렉션
    }

    @GetMapping("/listdetail")
    public String listDetail(@RequestParam("tradeNum") Long tradeNum, Model model) {
        AdminBoard trade = adminBoardRepository.findById(tradeNum).orElse(null);
        if (trade == null) {
            return "error/404"; // 거래를 찾을 수 없는 경우 404 페이지로 이동
        }
        model.addAttribute("trade", trade);
        return "trade/listde"; // listdetail.html 템플릿을 반환
    }


}