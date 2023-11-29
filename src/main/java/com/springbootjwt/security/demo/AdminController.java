package com.springbootjwt.security.demo;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read')")
    public String get(){
        return "GET:: admin controller";
    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin:create')")
    public String post(){
        return "POST:: admin controller";
    }
    @PutMapping
    @PreAuthorize("hasAnyAuthority('admin:update')")
    public String put(){
        return "PUT:: admin controller";
    }
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('admin:delete')")
    public String delete(){
        return "DELETE:: admin controller";
    }
}
