package io.github.abbassizied.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.abbassizied.sms.entities.Discount;
import io.github.abbassizied.sms.exceptions.DiscountNotFoundException;
import io.github.abbassizied.sms.repositories.DiscountRepository;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public List<Discount> listDiscounts() {
        return discountRepository.findAll();
    }

    @Override
    public Discount getDiscountById(long id) {
        return discountRepository.findById(id)
                .orElseThrow(() -> new DiscountNotFoundException("Invalid discount Id:" + id));
    }

    @Override
    public Discount saveDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    @Override
    public void deleteDiscountById(long id) {
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new DiscountNotFoundException("Invalid discount Id:" + id));
        discountRepository.delete(discount);
    }
}
