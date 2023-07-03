package com.minibackend.minibackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minibackend.minibackend.DTO.ProductDto;
import com.minibackend.minibackend.Entity.Product;
import com.minibackend.minibackend.Entity.Transaction;
import com.minibackend.minibackend.Service.ProductService;
import com.minibackend.minibackend.Service.TransactionService;
import com.minibackend.minibackend.utils.ProductUtilityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/inventory")
@Validated
public class ProductRestController {
    @Autowired
    ProductService productService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ProductUtilityService productUtilityService;

    // ======================================================
    @PostMapping("/product")
    public ResponseEntity<Product> add(@Valid @RequestBody Product product) {
        Product addedProduct = productService.add(product);
        return ResponseEntity.ok().body(addedProduct);
    }

    @PatchMapping("/product")
    public ResponseEntity<Product> updatePrice(@RequestBody Product product) {
        Product updatedProduct = productService.updatePrice(product.getName(), product.getPrice());
        return ResponseEntity.ok().body(updatedProduct);
    }

    // !------------------------------------------------------
    @GetMapping("/product/{name}")
    public ResponseEntity<Product> getProducts(@PathVariable String name) {
        Product product = productService.get(name);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/product/{name}")
    public ResponseEntity<Product> removeProduct(@PathVariable String name) {
        Product product = productService.remove(name);
        return ResponseEntity.ok().body(product);
    }

    // ======================================================

    @PutMapping("/product/purchase")
    public ResponseEntity<List<Product>> purchase(@Valid @RequestBody List<ProductDto> productDtos) {
        List<Product> products = productUtilityService.validateProduct(productDtos, false);
        products = productService.purchase(products);
        return ResponseEntity.ok().body(products);
    }

    @PutMapping("/product/sale")
    public ResponseEntity<List<Product>> sale(@Valid @RequestBody List<ProductDto> productDtos) {
        List<Product> products = productUtilityService.validateProduct(productDtos, true);

        products = productService.sale(products);
        return ResponseEntity.ok().body(products);
    }

    // ======================================================

    @GetMapping
    public ResponseEntity<List<Product>> get() {
        List<Product> products = productService.get();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/transactionCount")
    public ResponseEntity<Integer> getTransactionCount() {
        int totalTransactionCount = transactionService.get().size();
        return ResponseEntity.ok().body(totalTransactionCount);
    }

    @GetMapping("/transaction")
    public ResponseEntity<List<Transaction>> getTransactions() {
        List<Transaction> transactions = transactionService.get();
        return ResponseEntity.ok().body(transactions);
    }
}
