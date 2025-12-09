package br.edu.ifpb.padroes.store;

import br.edu.ifpb.padroes.customer.CustomerType;

import java.util.ArrayList;
import java.util.List;

public class DiscountStrategyFactory {
    public static List<DiscountStrategy> getAll(CustomerType customerType) {
        List<DiscountStrategy> strategies = new ArrayList<>();

        switch (customerType) {
            case VIP -> strategies.add(new VipDiscountStrategy());
            case PREMIUM -> strategies.add(new PremiumDiscountStrategy());
            case REGULAR -> strategies.add(new RegularDiscountStrategy());
        }

        strategies.add(new OldVinylDiscountStrategy());
        strategies.add(new PopPunkVipDiscountStrategy());
        return strategies;
    }
}
