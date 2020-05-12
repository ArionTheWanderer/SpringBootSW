package ru.itis.springsem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.springsem.form.ProductForm;
import ru.itis.springsem.model.Size;
import ru.itis.springsem.model.SizeEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long size_id;

    private Long product_id;

    private Integer pro_qty;

    public static ProductDto fromProductForm(ProductForm productForm) {
        Long longId = Long.parseLong(productForm.getSize_id());
        return ProductDto.builder().size_id(longId)
                .product_id(productForm.getProduct_id())
                .pro_qty(productForm.getPro_qty()).build();
    }
}
