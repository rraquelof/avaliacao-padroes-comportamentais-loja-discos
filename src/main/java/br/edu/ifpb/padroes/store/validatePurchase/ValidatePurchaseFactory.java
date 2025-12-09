package br.edu.ifpb.padroes.store;

import java.util.List;

public class ValidatePurchaseFactory {

    public static ValidatePurchaseChain create() {

        return new ValidatePurchaseChain(
                List.of(
                        new ValidatePurchaseStokStrategy(),
                        new ValidatePurchaseCreditStrategy(),
                        new ValidatePurchaseAgeStrategy()
                )
        );
    }
}
