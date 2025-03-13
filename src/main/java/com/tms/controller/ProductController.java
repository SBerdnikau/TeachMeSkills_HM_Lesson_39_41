package com.tms.controller;

import com.tms.model.Product;
import com.tms.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/create")
    public String getProductPage(){
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("product") Product product, Model model, HttpServletResponse response){
        Optional<Product> createdProduct = productService.createProduct(product);
        if(createdProduct.isEmpty()){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "Product creation failed");
            return "innerError";
        }
        model.addAttribute("product", createdProduct.get());
        return "product";
    }

    @GetMapping
    public String getProductEditPage(@RequestParam("productId") Long productId, Model model, HttpServletResponse response){
        Optional<Product> product = productService.getProductById(productId);
        if(product.isEmpty()){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            model.addAttribute("message", "Product not found id=" + product);
            return "innerError";
        }
        model.addAttribute("product", product.get());
        return "editUser";
    }

    @PostMapping("/{id}")
    public String getProductById(@PathVariable("id") Long id, Model model, HttpServletResponse response){
        Optional<Product> product = productService.getProductById(id);
        if(product.isEmpty()){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            model.addAttribute("message", "Product not found id=" + id);
            return "innerError";
        }
        response.setStatus(HttpServletResponse.SC_OK);
        model.addAttribute("product", product.get());
        return "product";
    }

    @PostMapping
    public String updateProduct(@ModelAttribute("product") Product product, Model model, HttpServletResponse response){
        Optional<Product> updatedProduct = productService.updateProduct(product);
        if(updatedProduct.isEmpty()){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "Product update failed");
            return "innerError";
        }
        response.setStatus(HttpServletResponse.SC_OK);
        model.addAttribute("product", updatedProduct.get());
        return "product";
    }

    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute("productId") Long productId, Model model, HttpServletResponse response){
        Optional<Product> deletedProduct = productService.deleteProduct(productId);
        if(deletedProduct.isEmpty()){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "Product delete failed");
            return "innerError";
        }
        response.setStatus(HttpServletResponse.SC_OK);
        model.addAttribute("product", deletedProduct.get());
        return "product";
    }
}
