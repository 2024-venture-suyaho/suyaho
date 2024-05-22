package com.venture.suyaho.deal.repository;

import com.venture.suyaho.deal.dto.TradeDTO;
import com.venture.suyaho.deal.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
    @Query(value = "SELECT * FROM trade WHERE category_id = (SELECT id FROM category WHERE name = :categoryName)", nativeQuery = true)
    List<Trade> findByCategoryName(@Param("categoryName") String categoryName);

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
    @Query("SELECT new com.venture.suyaho.deal.dto.TradeDTO(t) FROM Trade t")
    List<TradeDTO> findTradesWithSelectedFields();
;
}
