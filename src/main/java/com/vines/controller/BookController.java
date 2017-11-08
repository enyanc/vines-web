package com.vines.controller;

import com.vines.domain.Book;
import com.vines.domain.BookQuery;
import com.vines.service.BookQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/queryBook")
public class BookController {
    @Autowired
    BookQueryService bookQueryService;

    @RequestMapping("/findBookNoQuery")
    public String findBookNoQuery(ModelMap modelMap, @RequestParam(value = "pageIndex", defaultValue = "0") Integer page,
                                  @RequestParam(value = "size", defaultValue = "1") Integer size){
        Page<Book> datas = bookQueryService.findBookNoCriteria(page, size);
        modelMap.addAttribute("page", datas);
        System.out.println(datas.toString());
        return "index1";
    }

    @RequestMapping(value = "/findBookQuery",method = {RequestMethod.GET,RequestMethod.POST})
    public String findBookQuery(ModelMap modelMap, @RequestParam(value = "pageIndex", defaultValue = "0") Integer page,
                                @RequestParam(value = "size", defaultValue = "10") Integer size, BookQuery bookQuery){
        Page<Book> datas = bookQueryService.findBookCriteria(page, size, bookQuery);
        modelMap.addAttribute("page", datas);
        return "index2";
    }

    @RequestMapping(value = "/del",method = {RequestMethod.GET,RequestMethod.POST})
      public String delBookById(ModelMap modelMap,
                                @RequestParam(value = "bookId") Long id){
        bookQueryService.delById(id);
        return "redirect:/queryBook/findBookQuery";
    }

    @RequestMapping(value = "/query",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Book   qryBookById(ModelMap modelMap,
                              @RequestParam(value = "bookId") Long id){
        //以json格式返回对象，必须添加 @ResponseBody
        return bookQueryService.queryById(id);
    }


    @RequestMapping(value = "/queryFra",method = {RequestMethod.GET,RequestMethod.POST})
    public String   qryBookByIdFragment(ModelMap modelMap,
                              @RequestParam(value = "bookId") Long id){
        modelMap.addAttribute("book",  bookQueryService.queryById(id) );
        return "index2 :: edit2";
    }

    @RequestMapping(value = "/edit",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String   edit( @RequestBody Book book
                        ){
        bookQueryService.save(book);
        return "{'success':'true'}";
    }
}