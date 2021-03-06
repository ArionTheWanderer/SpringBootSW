package ru.itis.springsem.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "orders")
@Builder
@Entity
@Table(name = "products_list")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "small_image")
    private String smallImage;

    @Column(name = "large_image")
    private String largeImage;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "dimensions")
    private String dimensions;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "full_description")
    private String fullDescription;

    @Column(name = "cost")
    private Integer cost;

    @ManyToMany
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @ManyToMany
    @JoinTable(name = "product_color",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id"))
    private List<Color> colors;

    @ManyToMany
    @JoinTable(name = "product_size",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id"))
    private List<Size> sizes;

    @OneToMany(mappedBy = "product", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Order> orders;
}
