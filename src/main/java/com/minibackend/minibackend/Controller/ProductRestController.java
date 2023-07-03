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
import com.minibackend.minibackend.Service.DtoService;
import com.minibackend.minibackend.Service.ProductService;
import com.minibackend.minibackend.Service.TransactionService;
import com.minibackend.minibackend.utils.OrderTypes;

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
    private DtoService dtoService;

    // ======================================================
    @PostMapping("/product")
    public ResponseEntity<Product> add(@Valid @RequestBody Product product) {
        Product addedProduct = productService.add(product);
        return ResponseEntity.ok().body(addedProduct);
    }

    @PatchMapping("/product")
    public Product updatePrice(@RequestBody Product product) {
        Product updatedProduct = productService.updatePrice(product.getName(), product.getPrice());
        return productService.get(updatedProduct.getName());

    }

    // !------------------------------------------------------
    @GetMapping("/product/{name}")
    public Product getProducts(@PathVariable String name) {
        return productService.get(name);
    }

    @DeleteMapping("/product/{name}")
    public Product removeProduct(@PathVariable String name) {
        return productService.remove(name);
    }

    // ======================================================

    @PutMapping("/product/purchase")
    public Product purchase(@Valid @RequestBody ProductDto productDto) {
        Product product = dtoService.dtoToProduct(productDto);
        // Make purchase
        Product purchasedProduct = productService.purchase(product);

        Transaction transaction = Transaction.builder().products(List.of(purchasedProduct))
                .category(OrderTypes.PURCHASE)
                .build();
        // update the transaction
        transactionService.add(transaction);
        return purchasedProduct;

    }

    @PutMapping("/product/sale")
    public Transaction sale(@Valid @RequestBody List<ProductDto> productDtos) {
        List<Product> products = productDtos.stream()
                .map(product -> dtoService.dtoToProduct(product))
                .toList();
        // Make the sale
        List<Product> res = productService.sale(products);

        Transaction transaction = Transaction.builder()
                .products(res)
                .category(OrderTypes.SALE)
                .build();

        // update the transaction
        transactionService.add(transaction);

        return transaction;
    }

    // ======================================================

    @GetMapping
    public List<Product> get() {
        return productService.get();
    }

    // @GetMapping("/transaction")
    // public int getTransaction() {
    // return transactionService.get().size();
    // }
    @GetMapping("/transaction")
    public List<Transaction> getTransaction() {
        return transactionService.get();
    }
}
