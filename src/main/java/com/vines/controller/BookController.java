package com.vines.controller;

import com.vines.domain.Book;
import com.vines.domain.BookQuery;
import com.vines.service.BookQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/queryBook")
public class BookController {
    @Autowired
    BookQueryService bookQueryService;

    @RequestMapping("/findBookNoQuery")
    public String findBookNoQuery(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                  @RequestParam(value = "size", defaultValue = "5") Integer size){
        Page<Book> datas = bookQueryService.findBookNoCriteria(page, size);
        modelMap.addAttribute("datas", datas);
        return "index1";
    }

    @RequestMapping(value = "/findBookQuery",method = {RequestMethod.GET,RequestMethod.POST})
    public String findBookQuery(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                @RequestParam(value = "size", defaultValue = "5") Integer size, BookQuery bookQuery){
        Page<Book> datas = bookQueryService.findBookCriteria(page, size,bookQuery);
        modelMap.addAttribute("datas", datas);
        return "index2";
    }
}