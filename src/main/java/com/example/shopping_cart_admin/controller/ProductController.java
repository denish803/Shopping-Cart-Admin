package com.example.shopping_cart_admin.controller;


import com.example.shopping_cart_admin.dto.ProductDTO;
import com.example.shopping_cart_admin.repository.SubCategoryRepository;
import com.example.shopping_cart_admin.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/product")
@Log4j2
public class ProductController {


    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ProductService productService;


    @GetMapping("/insert")
    public ModelAndView insert() {
        return new ModelAndView("/product/product_insert")
                .addObject("subCategory", subCategoryRepository.findAll());
    }

    @PostMapping("/insert")
    public ModelAndView insert(ProductDTO productDTO, @RequestParam MultipartFile imageFile) throws Exception {

        // image name
        String imgName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();

        // save image
        String path = PathController.ImagePath() + "product\\"
                +  imgName;
        imageFile.transferTo(new File(path));

        // set image name
        productDTO.setImage(imgName);

        productService.insert(productDTO);
        return new ModelAndView("redirect:/product/insert");
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable int id) {
        return new ModelAndView("/product/product_update")
                .addObject("productList", productService.get(id));
    }

    @GetMapping(path = "/image", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<Resource> image(@RequestParam String imageName) throws Exception {
        log.info("work image");
        String path = PathController.ImagePath() + "product\\" + imageName;
        log.info("path : {}", path);
        final ByteArrayResource byteArrayResource = new ByteArrayResource(Files.readAllBytes(Paths.get(path)));
        log.info("byt array : {}", byteArrayResource);
        return ResponseEntity.status(HttpStatus.OK).contentLength(imageName.length()).body(byteArrayResource);

    }

    @PostMapping("/update")
    public ModelAndView update(ProductDTO productDTO) {
//        productService.update(productDTO);
        return new ModelAndView("redirect:/product/update");
    }


}
