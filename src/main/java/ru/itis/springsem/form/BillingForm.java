package ru.itis.springsem.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillingForm {
    private String billing_country;

    private String billing_streetAddress;

    private String billing_apartment;

    private String billing_city;

    private String billing_state;

    private String billing_phone;

    private String orderNotes;

    private String payment_method;
}
