package com.springboot.speedscape;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HomeController {

    private final ProductService productService;
    private final UserService userService;

    public HomeController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    //PRODUCT
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", productService.getProducts());
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    @PostMapping("/new-product")
    public String newProduct(@ModelAttribute NewProduct newProduct) {
        productService.create(newProduct);
        return "redirect:/";
    }

    @PostMapping("/multi-field-search")
    public String multiFieldSearch( //
        @ModelAttribute ProductSearch search, //
        Model model) {
            List<ProductEntity> searchResults = //
            productService.search(search);
        model.addAttribute("products", searchResults);
        return "index";
    }
    
    @PostMapping("/universal-search")
    public String universalSearch(@ModelAttribute UniversalSearch search, Model model) {
        List<ProductEntity> searchResults = productService.search(search);
        model.addAttribute("products", searchResults);
        return "index";
    }

    // USER
    @PostMapping("/new-user")
    public String newUser(@ModelAttribute NewUser newUser) {
        userService.create(newUser);
        return "redirect:/";
    }

    @PostMapping("/log-in")
    public String logIn( //
        @ModelAttribute UserSearch search, //
        Model model) {
            List<UserEntity> searchResults = //
            userService.search(search);

            UserEntity loggedInUser = searchResults.isEmpty() ? null : searchResults.get(0);
            model.addAttribute("loggedInUser", loggedInUser);

            // Agrega registros para verificar qué usuario se ha autenticado
            if (loggedInUser != null) {
                System.out.println("Usuario autenticado: " + loggedInUser.getName());
            } else {
                System.out.println("Ningún usuario autenticado");
            }

            return "index";
    }

    // @PostMapping("/delete-user")
    // public String deleteUser(@ModelAttribute UserDelete userDelete) {
    //     boolean deleted = userService.deleteUser(userDelete);
        
    //     if (deleted) {
    //         return "redirect:/";
    //     } else {
    //         return "redirect:/error";
    //     }
    // }
}  
