package br.com.stockbay.userstockbay.domain.product;
import br.com.stockbay.userstockbay.request.ProductRequest;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

@Table(name = "product")
@Entity(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Date dtCompra;
    private Number price_in_cents;
    private Boolean active;

    public Product(ProductRequest data) {
        this.name = data.name();
        this.price_in_cents = data.price_in_cents();
        this.dtCompra = data.dtCompra();
        this.active  = true;
    }
}
