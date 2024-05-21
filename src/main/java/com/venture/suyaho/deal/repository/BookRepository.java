// BookRepository.java
package com.venture.suyaho.deal.repository;

import com.venture.suyaho.deal.entity.Book;
import org.aspectj.weaver.ast.Var;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO book (trade_num, user_no, book_writing, book_cover, book_discoloration, book_damage, book_company) " +
            "VALUES (:tradeNum, :userNo, :bookWriting, :bookCover, :bookDiscoloration, :bookDamage, :bookCompany)", nativeQuery = true)
    void insertBook(@Param("tradeNum") int tradeNum,
                    @Param("userNo") int userNo,
                    @Param("bookWriting") Character bookWriting,
                    @Param("bookCover") Character bookCover,
                    @Param("bookDiscoloration") Character bookDiscoloration,
                    @Param("bookDamage") Character bookDamage,
                    @Param("bookCompany") Var bookCompany);
}