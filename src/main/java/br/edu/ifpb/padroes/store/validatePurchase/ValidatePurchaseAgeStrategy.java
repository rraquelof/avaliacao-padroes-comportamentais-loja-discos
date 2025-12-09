package br.edu.ifpb.padroes.store;

import br.edu.ifpb.padroes.customer.Customer;
import br.edu.ifpb.padroes.music.AgeRestriction;
import br.edu.ifpb.padroes.music.Album;

import java.time.LocalDate;

public class ValidatePurchaseAgeStrategy implements ValidatePurchaseStrategy {

    @Override
    public boolean validate(Customer customer, Album album) {
        if (album.getAgeRestriction() == AgeRestriction.PARENTAL_ADVISORY &&
                customer.getDateOfBirth().isAfter(LocalDate.now().minusYears(18))) {
            System.out.println("Validation failed: Age restriction");
            return false;
        }
        return true;
    }
}
