package com.experimentality.Store.web.controller;

import com.experimentality.Store.domain.service.PurchaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/client")
    @ApiOperation(value = "Find a purchases by client", notes = "This endpoint gets all the purchases of a client")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 403, message = "Forbiden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public Map<String, Object> getByClient(@RequestParam(required = false) Integer id) throws NotFoundException {

        if (id == null) throw new IllegalArgumentException("User id is required");

        return purchaseService.getByClient(id);
    }

}
