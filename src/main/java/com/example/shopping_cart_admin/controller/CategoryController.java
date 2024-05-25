package com.example.shopping_cart_admin.controller;

import com.example.shopping_cart_admin.dto.CategoryDTO;
import com.example.shopping_cart_admin.service.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Log4j2
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get-list")
    public ModelAndView view() {
        return new ModelAndView("category/category_view")
                .addObject("categoryData", categoryService.view());
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable int id) {
        CategoryDTO categoryDTO = categoryService.get(id);
        return new ModelAndView("category/category_update")
                .addObject("categoryData", categoryDTO);
    }

    @PostMapping("/update")
    public ModelAndView update(CategoryDTO categoryDTO) {
        log.info("update in controller for  category : {}", categoryDTO);
        categoryService.update(categoryDTO);
        return new ModelAndView("redirect:/category/get-list");
    }

    @GetMapping("/insert")
    public ModelAndView insert() {
        return new ModelAndView("/category/category_insert");
    }

    @PostMapping("/insert")
    public ModelAndView insert(CategoryDTO categoryDTO) {
        categoryService.insert(categoryDTO);
        return new ModelAndView("redirect:/category/get-list");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        categoryService.delete(id);
        return new ModelAndView("redirect:/category/get-list");
    }

}
