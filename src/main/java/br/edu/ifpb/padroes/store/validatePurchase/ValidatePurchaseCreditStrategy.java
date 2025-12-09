package br.edu.ifpb.padroes.store;

import br.edu.ifpb.padroes.customer.Customer;
import br.edu.ifpb.padroes.music.Album;

public class ValidatePurchaseCreditStrategy implements ValidatePurchaseStrategy {

    @Override
    public boolean validate(Customer customer, Album album) {
        if (customer.getCredit() < album.getPrice()) {
            System.out.println("Validation failed: Insufficient credit");
            return false;
        }
        return true;
    }
}
