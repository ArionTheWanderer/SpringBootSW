package ru.itis.springsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.springsem.dto.UserDto;
import ru.itis.springsem.form.BillingForm;
import ru.itis.springsem.model.*;
import ru.itis.springsem.repositories.BillingRepository;
import ru.itis.springsem.repositories.OrderRepository;
import ru.itis.springsem.security.details.UserDetailsImpl;
import ru.itis.springsem.services.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;

import static ru.itis.springsem.dto.UserDto.from;

@Scope("customScope")
@Controller
public class CheckoutController {
    @Autowired
    Cart cart;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BillingRepository billingRepository;


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/checkout")
    public String getCheckoutPage(ModelMap modelMap, Authentication authentication) {
        if (cart.getItemsQuan().size() == 0) {
            return "redirect:/cart";
        }
        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
        UserDto userFromServer = from(details.getUser());
        modelMap.addAttribute("userFromServer", userFromServer);
        modelMap.addAttribute("cart", cart);
        return "checkout";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/checkout")
    public String makeAnOrder(BillingForm billingForm, Authentication authentication) {
        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date nowDate = new Date();
        for (CartItemQuan cartItemQuan : cart.getItemsQuan()) {
            Order newOrder = Order.builder()
                    .date(simpleDateFormat.format(nowDate))
                    .status(Status.PROCESSING)
                    .total(cart.getTotals())
                    .owner(details.getUser())
                    .product(cartItemQuan.getCartItem().getProduct())
                    .build();

            BillingDetails billingDetails = BillingDetails.builder()
                    .country(billingForm.getBilling_country())
                    .streetAndHouse(billingForm.getBilling_streetAddress())
                    .apartment(billingForm.getBilling_apartment())
                    .region(billingForm.getBilling_state())
                    .phone(billingForm.getBilling_phone())
                    .orderNotes(billingForm.getOrderNotes())
                    .paymentMethod(billingForm.getPayment_method())
                    .order(newOrder)
                    .build();

            orderRepository.save(newOrder);
            billingRepository.save(billingDetails);
        }
        cart.clear();
        return "redirect:/";
    }
}
