package com.vines.service;

import com.vines.domain.Book;
import com.vines.domain.BookQuery;
import org.springframework.data.domain.Page;

public interface BookQueryService {
    Page<Book> findBookNoCriteria(Integer page, Integer size);
    Page<Book> findBookCriteria(Integer page,Integer size,BookQuery bookQuery);
    void delById(Long Id);
    Book  queryById(Long Id);
    void save(Book book);

}