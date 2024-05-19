package com.venture.suyaho.user;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trade_board")
public class AdminBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_num")
    private Long tradeNum;

    @Column(name = "trade_category", nullable = false)
    private String tradeCategory;

    @Column(name = "trade_title", nullable = false)
    private String tradeTitle;

    @Column(name = "trade_product", nullable = false)
    private String tradeProduct;

    @Column(name = "trade_quantity", nullable = false)
    private int tradeQuantity;

    @Column(name = "trade_price", nullable = false)
    private int tradePrice;

    @Column(name = "trade_text", nullable = false)
    private String tradeText;

    @Column(name = "trade_condition", nullable = false)
    private String tradeCondition;

    @Lob
    @Column(name = "trade_photo", nullable = false)
    private byte[] tradePhoto;

    @Column(name = "trade_time", nullable = false)
    private LocalDateTime tradeTime;

    @Column(name = "trade_complete", nullable = false)
    private char tradeComplete;

    @Column(name = "user_no", nullable = false)
    private Long userNo;

    // Getters and setters
}
