package com.experimentality.Store.web.controller;

import com.experimentality.Store.domain.dto.ImageDto;
import com.experimentality.Store.domain.dto.NewProductsDto;
import com.experimentality.Store.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/image")
    @ApiOperation(value = "New image", notes = "This endpoint stores a a list of images for products")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbiden")
    })
    public Map<String, Object> newImage(@Valid @RequestBody List<ImageDto> imagesPayload, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException("All or some mandatory fields are incomplete");
        }

        return productService.newImages(imagesPayload);
    }

    @PostMapping("/new")
    @Transactional
    @ApiOperation(value = "Create new products", notes = "This endpoint creates a new products at the database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public Map<String, Object> newProducts(@Valid @RequestBody List<NewProductsDto> productsPayload, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("All or some mandatory fields are incomplete");
        }

        return productService.newProducts(productsPayload);
    }

    @GetMapping("/dynamicfilter")
    @ApiOperation(value = "Dynamic products filter",
            notes = "This endpoint returns a list of products related with the param," +
                    " there are some optional values min = true (orders the  products by price ASC)" +
                    " max = true (orders the products by price DESC), counter = true (orders the products" +
                    " by search counter DESC) also with pagination through limit and offser, default values" +
                    " name = '', limit = 5, offset = 0, min-max-counter = false")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
    })
    public Map<String, Object> dynamicFilter(@RequestParam(required = false, defaultValue = "") String name,
                                             @RequestParam(required = false, defaultValue = "5") int limit,
                                             @RequestParam(required = false, defaultValue = "0") int offset,
                                             @RequestParam(required = false, defaultValue = "false") boolean min,
                                             @RequestParam(required = false, defaultValue = "false") boolean max,
                                             @RequestParam(required = false, defaultValue = "false") boolean counter) {

        String request = "";
        if (min & !max & !counter) request = "min";
        if (max & !min & !counter) request = "max";
        if (counter & !min & !max) request = "counter";
        System.out.println(request);

        return productService.dynamicFilter(name, limit, offset, request);
    }

    @GetMapping("/smartfilter")
    @ApiOperation(value = "Smart filter",
            notes = "This endpoint scans the database searching for all related with the param name")
    @ApiResponse(code = 200, message = "Ok")
    public Map<String, Object> smartFilter(@RequestParam(required = false, defaultValue = "") String name) {
        return productService.smartFilter(name);
    }

    @GetMapping("/specific/{id}")
    @ApiOperation(value = "Find a specific product",
            notes = "This endpoint finds a product by its name and everytime that is required a specific product " +
                    " increases its search counter")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public Map<String, Object> getByName(@PathVariable("id") int id) throws NotFoundException {
        return productService.getById(id);
    }
}
