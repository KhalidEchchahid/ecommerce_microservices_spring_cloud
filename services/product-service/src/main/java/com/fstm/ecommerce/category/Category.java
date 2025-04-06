package com.ali.ecommerce.category;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.fstm.ecommerce.product.Product;

@NoArgsConstructor
@AllArgsConstructor @Getter @Setter @Entity @Builder
public class Category {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> products;
}
