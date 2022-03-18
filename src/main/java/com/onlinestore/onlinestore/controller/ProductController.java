package com.onlinestore.onlinestore.controller;

import com.onlinestore.onlinestore.constants.ErrorMessage;
import com.onlinestore.onlinestore.constants.SuccessMessage;
import com.onlinestore.onlinestore.dto.request.ProductCreateDto;
import com.onlinestore.onlinestore.dto.response.CountDto;
import com.onlinestore.onlinestore.dto.response.ErrorMessageDto;
import com.onlinestore.onlinestore.dto.response.SuccessMessageDto;
import com.onlinestore.onlinestore.exception.ProductAlreadyExistException;
import com.onlinestore.onlinestore.exception.ProductNotFoundException;
import com.onlinestore.onlinestore.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity addProduct(@RequestBody ProductCreateDto product) {
        try {
            productService.addProduct(product);

            return new ResponseEntity(
                    new SuccessMessageDto(SuccessMessage.PRODUCT_ADDED),
                    HttpStatus.OK
            );
        } catch (ProductAlreadyExistException e) {
            e.printStackTrace();

            return new ResponseEntity(
                    new ErrorMessageDto(ErrorMessage.PRODUCT_ALREADY_EXIST),
                    HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            e.printStackTrace();

            return new ResponseEntity(
                    new ErrorMessageDto(ErrorMessage.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(params = {"id"})
    public ResponseEntity getProduct(@RequestParam long id) {
        try {
            return new ResponseEntity(
                    productService.getProduct(id),
                    HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            e.printStackTrace();

            return new ResponseEntity(
                    new ErrorMessageDto(ErrorMessage.PRODUCT_NOT_FOUND),
                    HttpStatus.BAD_REQUEST
            );
        } catch (RuntimeException e) {
            e.printStackTrace();

            return new ResponseEntity(
                    new ErrorMessageDto(ErrorMessage.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping(params = {"page"})
    public ResponseEntity getPageProducts(@RequestParam int page) {
        try {
            return new ResponseEntity(
                    productService.getPageProducts(page),
                    HttpStatus.OK
            );
        }catch (RuntimeException e) {
            e.printStackTrace();

            return new ResponseEntity(
                    new ErrorMessageDto(ErrorMessage.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping
    @RequestMapping("/count")
    public ResponseEntity getCountProducts() {
        try {
            return new ResponseEntity(
                    new CountDto(productService.getCountProducts()),
                    HttpStatus.OK
            );
        } catch (RuntimeException e) {
            e.printStackTrace();

            return new ResponseEntity(
                    new ErrorMessageDto(ErrorMessage.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity deleteProduct(@RequestParam long id) {
        try {
            productService.deleteProduct(id);

            return new ResponseEntity(
                    new SuccessMessageDto(SuccessMessage.PRODUCT_DELETED),
                    HttpStatus.OK
            );

        } catch (ProductNotFoundException e) {
            e.printStackTrace();

            return new ResponseEntity(
                    new ErrorMessageDto(ErrorMessage.PRODUCT_NOT_FOUND),
                    HttpStatus.BAD_REQUEST
            );
        } catch (RuntimeException e) {
            e.printStackTrace();

            return new ResponseEntity(
                    new ErrorMessageDto(ErrorMessage.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
