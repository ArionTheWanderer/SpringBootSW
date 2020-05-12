package ru.itis.springsem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.springsem.dto.ProductDto;
import ru.itis.springsem.model.Cart;
import ru.itis.springsem.model.CartItem;
import ru.itis.springsem.model.Product;
import ru.itis.springsem.model.Size;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ProductService productService;

    @Autowired
    SizeService sizeService;

    @Autowired
    Cart cart;

    @Override
    public void addItem(ProductDto productDto) {
        Long productId = productDto.getProduct_id();
        Optional<Product> product = productService.findById(productId);
        Optional<Size> size = sizeService.getSizeById(productDto.getSize_id());
        if (product.isPresent() && size.isPresent()) {
            CartItem cartItem = createCartItem(product.get(), size.get(), productDto);
            cart.getItems().add(cartItem);
            Integer totals = cart.getTotals();
            totals = totals + cartItem.getTotal();
            cart.setTotals(totals);
        }
    }

    private CartItem createCartItem(Product product, Size size, ProductDto productDto) {
        return CartItem.builder()
                .id(CartItem.ITEMS_ID++)
                .product(product)
                .size(size)
                .quantity(productDto.getPro_qty())
                .total(productDto.getPro_qty() * product.getCost())
                .build();
    }


}
