package tech.itpark.itparkfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Set;

@Entity
@Table(schema = "storage", name = "category_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = "productTable")
public class Category {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    @NotNull
    private String id;

    @Column(name = "category_name", unique = true)
    @NotNull
    @NotBlank
    private String categoryName;

    @Column(name = "amount", columnDefinition = "BIGINT")
    private BigInteger amount;

    @Column(name = "picture", columnDefinition = "TEXT")
    private String picture;

    @Column(name = "description", columnDefinition = "TEXT")
    @NotNull
    private String description;

    @OneToMany(mappedBy = "categoryTable", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Product> productTable;

}
