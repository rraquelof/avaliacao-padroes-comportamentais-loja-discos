package br.edu.ifpb.padroes.store;

import br.edu.ifpb.padroes.customer.Customer;
import br.edu.ifpb.padroes.music.Album;

public interface ValidatePurchaseStrategy {
    boolean validate(Customer customer, Album album);
}
