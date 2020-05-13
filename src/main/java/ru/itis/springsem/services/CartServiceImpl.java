package ru.itis.springsem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.springsem.dto.ProductDto;
import ru.itis.springsem.model.*;

import java.util.List;
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
            CartItem itemCandidate = createCartItem(product.get(), size.get());
            // System.out.println("итем кандидат: " + itemCandidate.toString());
            boolean f = false;
            List<CartItemQuan> a = cart.getItemsQuan();
            for (CartItemQuan cartItemQuan : a) {
                /*System.out.println("ЗАШЕЛ");*/
                CartItem cartItem = cartItemQuan.getCartItem();
                // System.out.println("итем из списка: " + cartItemQuan.getCartItem().getSize().getSize().name() + "" + cartItem.getProduct().toString());
                // System.out.println("ПЕРЕД УСЛОВИЕМ");
                if (itemCandidate.getProduct().getId().equals(cartItem.getProduct().getId()) &&
                        itemCandidate.getSize().getSize().name().equals(cartItem.getSize().getSize().name())) {
                    // System.out.println("ПОСЛЕ УСЛОВИЯ");
                    cartItemQuan.setQuantity(cartItemQuan.getQuantity() + productDto.getPro_qty());
                    f = true;
                    // System.out.println("AAAA" + cart.getItemsQuan().size());
                    break;
                }
            }
            if (!f) {
                CartItemQuan itemQuan = createCartItemQuan(product.get(), size.get(), productDto);
                cart.getItemsQuan().add(itemQuan);
                Integer totals = cart.getTotals();
                totals = totals + itemQuan.getTotal();
                cart.setTotals(totals);
                // System.out.println(cart.getItemsQuan().size());
            }
        }
    }

    private CartItem createCartItem(Product product, Size size) {
        return CartItem.builder()
                .product(product)
                .size(size)
                .build();
    }

    private CartItemQuan createCartItemQuan(Product product, Size size, ProductDto productDto) {
        return CartItemQuan.builder()
                .id(CartItemQuan.ITEMS_ID++)
                .cartItem(createCartItem(product, size))
                .quantity(productDto.getPro_qty())
                .total(productDto.getPro_qty() * product.getCost())
                .build();
    }
}
