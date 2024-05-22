package com.venture.suyaho.dto;

import com.venture.suyaho.domain.User;
import jakarta.persistence.*;

@Entity
@Table(name = "lost_item")
public class LostItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lost_num", nullable = false)
    private Long lostNum;

    @Column(name = "lost_title", nullable = false)
    private String lostTitle;

    @Column(name = "lost_position", nullable = false)
    private String lostPosition;

    @Column(name = "lost_text", nullable = false)
    private String lostText;

    @Lob
    @Column(name = "lost_photo")
    private byte[] lostPhoto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no", nullable = true)
    private User user;
}