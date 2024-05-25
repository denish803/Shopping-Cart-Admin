package com.example.shopping_cart_admin.controller;


import com.example.shopping_cart_admin.dto.CategoryDTO;
import com.example.shopping_cart_admin.dto.SubCategoryDTO;
import com.example.shopping_cart_admin.service.CategoryService;
import com.example.shopping_cart_admin.service.SubCategoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Log4j2
@RestController
@RequestMapping("/sub-category")
public class SubCategoryController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get-list")
    public ModelAndView getList() {
        log.info("data model : {}", subCategoryService.getList());

        String sql = "select sc.id, sc.name, sc.category_id, c.name category_name from sub_category sc inner join category c on " +
                "sc.category_id = c.id";
//        Map<String, Object> data = new HashMap<>();
        List<Map<String, Object>> fullData = jdbcTemplate.queryForList(sql);
        log.info("Full data : {}", fullData);

        return new ModelAndView("sub_category/sub_category_view")
                .addObject("subCategoryData", subCategoryService.getList())
                .addObject("getCategory", categoryService);
    }

    @GetMapping("/insert")
    public ModelAndView insert() {
        return new ModelAndView("/sub_category/sub_category_insert")
                .addObject("categoryList",  categoryService.view());
    }

    @PostMapping("/insert")
    public ModelAndView insert(SubCategoryDTO subCategoryDTO) {
        subCategoryService.insert(subCategoryDTO);
        log.info("insert work successfully : {}", subCategoryDTO);
        return new ModelAndView("redirect:/sub-category/get-list");
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable int id) {

//        String sql = "select * from sub_category sc inner join category c on sc.categoryId = c.id ";
        String sql = "select * from sub_category sc inner join category c on sc.category_id  = c.id";
//        Map<String, Object> data = new HashMap<>();
//        data.put("id", id);
//        List<Map<String, Object>> maps = namedParameterJdbcTemplate.queryForList(sql, data);

        log.info("sub Category Update Data with Inner Join : {} ", jdbcTemplate.queryForList(sql) );
        List<Map<String, Object>> categoryAndSubCategoryData = jdbcTemplate.queryForList(sql);
        log.info("sub Category Update Data with Inner Join : {} ", categoryAndSubCategoryData);

        return new ModelAndView("/sub_category/sub_category_update")
                .addObject("subCategory", subCategoryService.get(id))
                .addObject("categoryList", categoryService);
    }

    @PostMapping("/update")
    public ModelAndView update(SubCategoryDTO subCategoryDTO) {
        subCategoryService.update(subCategoryDTO);
        return new ModelAndView("redirect:/sub-category/get-list");
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        subCategoryService.delete(id);
        return new ModelAndView("redirect:/sub-category/get-list");
    }

}
