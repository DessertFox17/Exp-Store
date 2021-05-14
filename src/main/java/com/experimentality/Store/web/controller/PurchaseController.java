package com.experimentality.Store.web.controller;

import com.experimentality.Store.domain.dto.NewPurchaseDto;
import com.experimentality.Store.domain.dto.UpdatePurchaseStatusDto;
import com.experimentality.Store.domain.service.PurchaseService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/user/{id}")
    @ApiOperation(value = "Find a purchases by its user id", notes = "This endpoint gets all the purchases of a client")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 403, message = "Forbiden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public Map<String, Object> getByClient(@PathVariable("id") Integer id) throws NotFoundException {

        return purchaseService.getByClient(id);
    }

    @GetMapping("/cart/{id}")
    @ApiOperation(value = "Find the purchases in cart",
            notes = "This endpoint gets the purchases in cart of an user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 403, message = "Forbiden"),
    })
    public Map<String, Object> getByUserandStatus(@PathVariable("id") int id) throws NotFoundException {
        return purchaseService.getByUserandStatus(id);
    }

    @PutMapping("/update")
    @Transactional
    @ApiOperation(value = "Update purchase status",
            notes = "This endpoint updates the status of a purchase")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbiden")
    })
    public Map<String, Object> updatePurchase(@Valid @RequestBody UpdatePurchaseStatusDto purchasePayload, BindingResult bindingResult) throws NotFoundException {
        if (bindingResult.hasErrors()) throw new IllegalArgumentException("All or some mandatory fields are incomplete");

        return purchaseService.updatePurchase(purchasePayload);
    }

    @PostMapping("/new")
    @Transactional
    @ApiOperation(value = "New Purchase", notes = "This endpoint creates a new purchase")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbiden")
    })
    public Map<String, Object> newPurchase(@Valid @RequestBody NewPurchaseDto purchasePayload, BindingResult bindingResult) throws NotFoundException {
        if(bindingResult.hasErrors()) throw new IllegalArgumentException("All or some mandatory fields are incomplete");

        return purchaseService.newPurchase(purchasePayload);
    }

}
