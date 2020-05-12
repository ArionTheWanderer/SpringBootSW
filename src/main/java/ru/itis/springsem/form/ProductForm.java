package ru.itis.springsem.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductForm {
    private String size_id;

    private Long product_id;

    private Integer pro_qty;
}
