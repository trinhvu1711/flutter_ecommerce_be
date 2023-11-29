package com.springbootjwt.security.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/management")
@Tag(name = "Management")
public class ManagementController {
    @Operation(
            description = "Get end point for manager",
            summary = "This is for management get end point",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping
    public String get(){
        return "GET:: manager controller";
    }
    @PostMapping
    public String post(){
        return "POST:: manager controller";
    }
    @PutMapping
    public String put(){
        return "PUT:: manager controller";
    }
    @DeleteMapping
    public String delete(){
        return "DELETE:: manager controller";
    }
}
