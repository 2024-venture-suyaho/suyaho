package com.venture.suyaho.deal.service;

import com.google.errorprone.annotations.Var;
import com.venture.suyaho.deal.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public static void saveBook(int tradeNum, int userNo, Character bookWriting, Character bookCover,Character bookDiscoloration, Character bookDamage, Var bookCompany) {
        bookRepository.insertBook(tradeNum, userNo, bookWriting, bookCover, bookDiscoloration, bookDamage, bookCompany);
    }
}