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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDtCompra() {
        return dtCompra;
    }

    public void setDtCompra(Date dtCompra) {
        this.dtCompra = dtCompra;
    }

    public Number getPrice_in_cents() {
        return price_in_cents;
    }

    public void setPrice_in_cents(Number price_in_cents) {
        this.price_in_cents = price_in_cents;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Product(ProductRequest data) {
        this.name = data.name();
        this.price_in_cents = data.price_in_cents();
        this.dtCompra = data.dtCompra();
        this.active  = true;
    }
}
