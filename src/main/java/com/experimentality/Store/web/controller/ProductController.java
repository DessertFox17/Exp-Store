package com.experimentality.Store.web.controller;

import com.experimentality.Store.domain.dto.NewProductsDto;
import com.experimentality.Store.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/new")
    @ApiOperation(value = "Create new products", notes = "This endpoint creates a new products at the database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public Map<String, Object> newProducts(@Valid @RequestBody List<NewProductsDto> productsPayload, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException("All or some mandatory fields are incomplete");
        }

        return productService.newProducts(productsPayload);
    }

    @GetMapping("/dynamicfilter")
    @ApiOperation(value = "Dynamic products filter",
            notes = "This endpoint returns a list of products related with the param," +
                    " with pagination and ordered by their descending search counter")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
    })
    public Map<String, Object> dynamicFilter(@RequestParam(required = false, defaultValue = "") String name,
                                             @RequestParam(required = false, defaultValue = "5") int limit,
                                             @RequestParam(required = false, defaultValue = "0") int offset){
        return productService.dynamicFilter(name,limit,offset);
    }

    @GetMapping("/specific")
    @ApiOperation(value = "Find a specific product",
            notes = "This endpoint finds a product by its name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public Map<String, Object> getByName(@RequestParam(required = false) String name) throws NotFoundException {

        if(name == null) throw new IllegalArgumentException("Name of product not found");

        return productService.getByName(name);
    }
}
