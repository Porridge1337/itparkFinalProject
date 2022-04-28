package tech.itpark.itparkfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(schema = "storage", name = "product_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    @NotNull
    private String id;

    @Column(name = "product_name")
    @NotNull
    private String productName;

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    @Column(name = "amount", columnDefinition = "BIGINT")
    @NotNull
    private BigInteger amount;

    @Column(name = "picture", columnDefinition = "TEXT")
    private String picture;

    @Column(name = "description", columnDefinition = "TEXT")
    @NotNull
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryTable;
}
