package br.com.stockbay.userstockbay.controller;

import br.com.stockbay.userstockbay.domain.product.Product;
import br.com.stockbay.userstockbay.repositery.ProdcutRepositery;
import br.com.stockbay.userstockbay.request.ProductRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProdcutRepositery productRepositery;

    @GetMapping
    public ResponseEntity getAllProduct() {
        var allProduct = productRepositery.findAllByActiveTrue();
        return ResponseEntity.ok(allProduct);
    }

    @PostMapping
    public ResponseEntity postProduct(@RequestBody @Valid ProductRequest data) {
        Product newProduct = new Product(data);
        productRepositery.save(newProduct);
        System.out.println(data);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Product> putProduct(@RequestBody @Valid ProductRequest data) {
        Optional<Product> optionalProduct = productRepositery.findById(data.id());

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setPrice_in_cents(data.price_in_cents());
            product.setDtCompra(data.dtCompra());
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable String id) {
        Optional<Product> optionalProduct = productRepositery.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setActive(false);
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
