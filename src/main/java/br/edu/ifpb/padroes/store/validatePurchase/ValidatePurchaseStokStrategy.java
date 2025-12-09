package br.edu.ifpb.padroes.store;

import br.edu.ifpb.padroes.customer.Customer;
import br.edu.ifpb.padroes.music.Album;

public class ValidatePurchaseStokStrategy implements ValidatePurchaseStrategy{

    @Override
    public boolean validate(Customer customer, Album album) {
        if (album.getStock() <= 0) {
            System.out.println("Validation failed: Out of stock");
            return false;
        }
        return true;
    }
}
