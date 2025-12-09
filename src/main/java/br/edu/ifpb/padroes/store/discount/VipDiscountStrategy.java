package br.edu.ifpb.padroes.store;

import br.edu.ifpb.padroes.customer.CustomerType;
import br.edu.ifpb.padroes.music.Album;

public class VipDiscountStrategy implements DiscountStrategy{
    @Override
    public double calculate(Album album, CustomerType customerType) {
        if (customerType.equals(CustomerType.VIP)) {
            return album.getPrice() * 0.20;
        }
        return 0;
    }
}
