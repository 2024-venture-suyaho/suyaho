package com.venture.suyaho.repository;

import com.venture.suyaho.domain.TradeBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<TradeBoard, Long> {



/*
    @Query(value = "SELECT td FROM trade td WHERE category_id = (SELECT id FROM category WHERE name = :categoryName)", nativeQuery = true)
    List<TradeBoard> findByCategoryName(@Param("categoryName") String categoryName);

    @Modifying
    @Query(value = "INSERT INTO trade (title, book_writing, book_cover, book_discoloration, book_damage, author, publisher, product_name, quantity, price, description, category_id, trade_image) " +
            "VALUES (:title, :bookWriting, :bookCover, :bookDiscoloration, :bookDamage, :author, :publisher, :productName, :quantity, :price, :description, :categoryId, :imageData)", nativeQuery = true)
    void saveTradeWithQuery(@Param("title") String title,
                            @Param("bookWriting") Character bookWriting,
                            @Param("bookCover") Character bookCover,
                            @Param("bookDiscoloration") Character bookDiscoloration,
                            @Param("bookDamage") Character bookDamage,
                            @Param("author") String author,
                            @Param("publisher") String publisher,
                            @Param("productName") String productName,
                            @Param("quantity") int quantity,
                            @Param("price") double price,
                            @Param("description") String description,
                            @Param("categoryId") Long categoryId,
                            @Param("imageData") byte[] imageData);
    @Query("SELECT new com.venture.suyaho.dto.TradeDTO(t) FROM TradeBoard t")
    List<TradeDTO> findTradesWithSelectedFields();
*/

}
