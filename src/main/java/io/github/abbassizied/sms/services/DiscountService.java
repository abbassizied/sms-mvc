package io.github.abbassizied.sms.services;

import java.util.List;

import io.github.abbassizied.sms.entities.Discount;

public interface DiscountService {

    List<Discount> listDiscounts();

    Discount getDiscountById(long id);

    Discount saveDiscount(Discount discount);

    void deleteDiscountById(long id);
}
