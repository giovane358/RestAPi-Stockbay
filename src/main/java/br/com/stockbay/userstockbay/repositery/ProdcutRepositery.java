package br.com.stockbay.userstockbay.repositery;

import br.com.stockbay.userstockbay.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProdcutRepositery extends JpaRepository<Product, String> {


    List<Product> findAllByActiveTrue();
}
