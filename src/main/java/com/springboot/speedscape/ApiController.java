package com.springboot.speedscape;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class ApiController {
    private final ProductService productService;
    private final UserService userService;

    public ApiController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/api/products")
    public List<ProductEntity> allProducts() {
        return productService.getProducts();
    }
    @PostMapping("/api/products")
    public ProductEntity newProduct(@RequestBody NewProduct newProduct) {
        return productService.create(newProduct);
    }
    
    @GetMapping("/api/users")
    public List<UserEntity> allUsers() {
        return userService.getUsers();
    }
    @PostMapping("/api/users")
    public UserEntity newUser(@RequestBody NewUser newUser) {
        return userService.create(newUser);
    }
}
