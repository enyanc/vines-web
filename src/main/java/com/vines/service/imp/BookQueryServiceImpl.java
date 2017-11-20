package com.vines.service.imp;

import com.vines.domain.Book;
import com.vines.domain.BookQuery;
import com.vines.repository.BookRepository;
import com.vines.service.BookQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service(value="bookQueryService")
public class BookQueryServiceImpl implements BookQueryService {
    @Resource
    BookRepository bookRepository;
    @Override
    public Page<Book> findBookNoCriteria(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> findBookCriteria(Integer page, Integer size, final BookQuery bookQuery) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.fromString(bookQuery.getOrder()), bookQuery.getSort()==null?"id":bookQuery.getSort());
        Page<Book> bookPage = bookRepository.findAll(new Specification<Book>(){
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(null!=bookQuery.getName()&&!"".equals(bookQuery.getName())){
                    list.add(criteriaBuilder.equal(root.get("name").as(String.class), bookQuery.getName()));
                }
                if(null!=bookQuery.getIsbn()&&!"".equals(bookQuery.getIsbn())){
                    list.add(criteriaBuilder.equal(root.get("isbn").as(String.class), bookQuery.getIsbn()));
                }
                if(null!=bookQuery.getAuthor()&&!"".equals(bookQuery.getAuthor())){
                    list.add(criteriaBuilder.equal(root.get("author").as(String.class), bookQuery.getAuthor()));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageable);
        return bookPage;
    }


    @Override
    public void delById(Long id) {
        bookRepository.delete(id);
    }

    @Override
    public Book queryById(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public void save(Book book) {
        bookRepository.saveAndFlush(book);
    }


}