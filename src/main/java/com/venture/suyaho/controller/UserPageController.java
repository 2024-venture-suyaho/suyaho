package com.venture.suyaho.controller;

import com.venture.suyaho.domain.AdminBoard;
import com.venture.suyaho.domain.Book;
import com.venture.suyaho.domain.User;
import com.venture.suyaho.repository.UserRepository;
import com.venture.suyaho.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@RequestMapping("/api")
public class UserPageController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TradeService tradeService;


    @PostMapping("/users/changeMajor")
    public ResponseEntity<?> changeMajor(@RequestBody ChangeMajorRequest request) {
        User user = userRepository.findById(request.getUserNo()).orElse(null);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        if (!user.getUserPwd().equals(request.getCurrentPassword())) {
            return new ResponseEntity<>("Current password is incorrect", HttpStatus.BAD_REQUEST);
        }
        user.setUserMajor(request.getNewMajor());
        userRepository.save(user);
        return new ResponseEntity<>("Major changed successfully", HttpStatus.OK);
    }

    @PostMapping("/users/changePhone")
    public ResponseEntity<?> changePhone(@RequestBody ChangePhoneRequest request) {
        User user = userRepository.findById(request.getUserNo()).orElse(null);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        if (!user.getUserPwd().equals(request.getCurrentPassword())) {
            return new ResponseEntity<>("Current password is incorrect", HttpStatus.BAD_REQUEST);
        }
        user.setUserPhone(request.getNewPhoneNumber());
        userRepository.save(user);
        return new ResponseEntity<>("Phone number changed successfully", HttpStatus.OK);
    }

    @PostMapping("/users/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        User user = userRepository.findById(request.getUserNo()).orElse(null);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        if (!user.getUserPwd().equals(request.getCurrentPassword())) {
            return new ResponseEntity<>("Current password is incorrect", HttpStatus.BAD_REQUEST);
        }
        user.setUserPwd(request.getNewPassword());
        userRepository.save(user);
        return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
    }

    @PostMapping("/users/uploadProfileImage")
    public ResponseEntity<?> uploadProfileImage(@RequestParam("file") MultipartFile file, @RequestParam("userNo") Long userNo) {
        try {
            User user = userRepository.findById(userNo).orElse(null);
            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
            // 파일 크기 검증 (2MB 이하로 제한)
            if (file.getSize() > 2 * 1024 * 1024) {
                return new ResponseEntity<>("File is too large. Max size is 2MB.", HttpStatus.BAD_REQUEST);
            }
            // 이미지 데이터를 byte 배열로 변환하여 저장
            byte[] imageBytes = file.getBytes();
            user.setUserImg(imageBytes);
            userRepository.save(user);
            // 이미지 URL 생성
            String imageUrl = generateImageUrl(userNo);
            return ResponseEntity.ok().body(Collections.singletonMap("imageUrl", imageUrl));
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 이미지 URL 생성 메서드
    private String generateImageUrl(Long userNo) {
        return "/api/users/profileImage/" + userNo;
    }

    @GetMapping("/users/profileImage/{userNo}")
    public ResponseEntity<byte[]> getUserProfileImage(@PathVariable Long userNo) {
        User user = userRepository.findById(userNo).orElse(null);
        if (user == null || user.getUserImg() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        byte[] imageBytes = user.getUserImg();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    @PostMapping("/trade/write")
    public ResponseEntity<?> createTrade(
            @RequestParam("image") MultipartFile image,
            @RequestParam("title") String title,
            @RequestParam("productName") String productName,
            @RequestParam("quantity") int quantity,
            @RequestParam("price") int price,
            @RequestParam("description") String description,
            @RequestParam("tradeCondition") String tradeCondition,
            @RequestParam("bookWriting") String bookWriting,
            @RequestParam("bookCover") String bookCover,
            @RequestParam("bookDiscoloration") String bookDiscoloration,
            @RequestParam("bookDamage") String bookDamage,
            @RequestParam("publisher") String publisher,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("userNo") Long userNo) {

        try {
            User user = userRepository.findById(userNo).orElse(null);
            if (user == null) {
                return ResponseEntity.status(404).body("User not found");
            }

            AdminBoard adminBoard = new AdminBoard();
            adminBoard.setTradeCategory(categoryId.toString());
            adminBoard.setTradeTitle(title);
            adminBoard.setTradeProduct(productName);
            adminBoard.setTradeQuantity(quantity);
            adminBoard.setTradePrice(price);
            adminBoard.setTradeText(description);
            adminBoard.setTradeCondition(tradeCondition);
            adminBoard.setTradePhoto(image.getBytes());
            adminBoard.setTradeTime(LocalDateTime.now());
            adminBoard.setTradeComplete('N');
            adminBoard.setUser(user);

            Book book = new Book();
            book.setBookWriting(bookWriting.charAt(0));
            book.setBookCover(bookCover.charAt(0));
            book.setBookDiscoloration(bookDiscoloration.charAt(0));
            book.setBookDamage(bookDamage.charAt(0));
            book.setBookCompany(publisher);
            book.setUserNo(userNo);

            tradeService.createTrade(adminBoard, book);

            return ResponseEntity.ok("Trade created successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to create trade");
        }
    }
}

class ChangeMajorRequest {
    private Long userNo;
    private String currentPassword;
    private String newMajor;

    // Getters and Setters

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewMajor() {
        return newMajor;
    }

    public void setNewMajor(String newMajor) {
        this.newMajor = newMajor;
    }
}

class ChangePhoneRequest {
    private Long userNo;
    private String currentPassword;
    private String newPhoneNumber;

    // Getters and Setters

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPhoneNumber() {
        return newPhoneNumber;
    }

    public void setNewPhoneNumber(String newPhoneNumber) {
        this.newPhoneNumber = newPhoneNumber;
    }
}

class ChangePasswordRequest {
    private Long userNo;
    private String currentPassword;
    private String newPassword;

    // Getters and Setters

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
