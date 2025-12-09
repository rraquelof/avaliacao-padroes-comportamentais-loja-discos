package br.edu.ifpb.padroes.store;

import br.edu.ifpb.padroes.customer.Customer;
import br.edu.ifpb.padroes.music.Album;

import java.util.List;

public class ValidatePurchaseChain {

    private final List<ValidatePurchaseStrategy> validators;

    public ValidatePurchaseChain(List<ValidatePurchaseStrategy> validators) {
        this.validators = validators;
    }

    public boolean validate(Customer customer, Album album) {
        for (ValidatePurchaseStrategy validator : validators) {
            if (!validator.validate(customer, album)) {
                return false;
            }
        }
        return true;
    }
}
