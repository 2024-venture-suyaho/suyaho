package com.venture.suyaho.controller;

import com.venture.suyaho.domain.AdminBoard;
import com.venture.suyaho.domain.Book;
import com.venture.suyaho.domain.User;
import com.venture.suyaho.repository.AdminBoardRepository;
import com.venture.suyaho.repository.BookRepository;
import com.venture.suyaho.repository.UserRepository;
import com.venture.suyaho.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserPageController {

    @Autowired
    private AdminBoardRepository adminBoardRepository;

    @Autowired
    private BookRepository bookRepository;

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
            @RequestParam("categoryId") Integer categoryId,
            @RequestParam("bookWriting") String bookWriting,
            @RequestParam("bookCover") String bookCover,
            @RequestParam("bookDiscoloration") String bookDiscoloration,
            @RequestParam("bookDamage") String bookDamage,
            @RequestParam("title") String title,
            @RequestParam("publisher") String publisher,
            @RequestParam("tradeProduct") String tradeProduct,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("price") Integer price,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image) {
        try {
            // AdminBoard 엔티티 생성 및 저장
            AdminBoard adminBoard = new AdminBoard();
            adminBoard.setTradeCategory(categoryId.toString());
            adminBoard.setTradeTitle(title);
            adminBoard.setTradeProduct(tradeProduct);
            adminBoard.setTradeQuantity(quantity);
            adminBoard.setTradePrice(price);
            adminBoard.setTradeText(description);
            adminBoard.setTradeCondition(
                    "필기 흔적: " + (bookWriting.equals("true") ? "없음" : "있음") + ", " +
                            "변색: " + (bookDiscoloration.equals("true") ? "없음" : "있음") + ", " +
                            "훼손: " + (bookDamage.equals("true") ? "없음" : "있음")
            );
            adminBoard.setTradePhoto(image.getBytes());
            adminBoard.setTradeTime(LocalDateTime.now());
            adminBoard.setTradeComplete('N');
            adminBoard.setUser(userRepository.findById(1L).orElseThrow()); // 예제 사용자 ID

            AdminBoard savedAdminBoard = tradeService.saveAdminBoard(adminBoard);

            // Book 엔티티 생성 및 저장
            Book book = new Book();
            book.setTradeNum(savedAdminBoard.getTradeNum()); // trade_num 값을 AdminBoard의 trade_num으로 설정
            book.setBookCompany(publisher);
            book.setBookCover(bookCover.equals("true") ? 'Y' : 'N');
            book.setBookDamage(bookDamage.equals("true") ? 'Y' : 'N');
            book.setBookDiscoloration(bookDiscoloration.equals("true") ? 'Y' : 'N');
            book.setBookWriting(bookWriting.equals("true") ? 'Y' : 'N');
            book.setUserNo(1L);  // 예시로 설정한 user_no 값

            tradeService.saveBook(book);

            return ResponseEntity.ok().body("Trade created successfully");
        } catch (Exception e) {
            // 자세한 로그 추가
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
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
