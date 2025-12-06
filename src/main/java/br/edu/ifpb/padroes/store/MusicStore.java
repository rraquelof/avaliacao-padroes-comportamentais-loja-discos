package br.edu.ifpb.padroes.store;

import br.edu.ifpb.padroes.customer.Customer;
import br.edu.ifpb.padroes.customer.CustomerType;
import br.edu.ifpb.padroes.music.AgeRestriction;
import br.edu.ifpb.padroes.music.Album;
import br.edu.ifpb.padroes.store.notification.NotificationInterested;
import br.edu.ifpb.padroes.store.notification.PurchaseNotification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MusicStore {

    private List<Album> inventory = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public void addMusic(Album album) {
        inventory.add(album);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Album> searchMusic(SearchStrategy strategy, String searchTerm) {
        return strategy.search(inventory, searchTerm);
    }

    public double calculateDiscount(Album album, CustomerType customerType) {
        double discount = 0;

        List<DiscountStrategy> strategies = DiscountStrategyFactory.getAll(customerType);

        for(DiscountStrategy strategy : strategies){
            discount += strategy.calculate(album, customerType);
        }

        return discount;
    }

    public void purchaseMusic(Customer customer, Album album) {
        if (validatePurchase(customer, album)) {
            double discount = calculateDiscount(album, customer.getType());
            double finalPrice = album.getPrice() - discount;

            PurchaseNotification purchaseNote = new PurchaseNotification(album,customer, discount, finalPrice);
            purchaseNote.createNotification();

            album.decreaseStock();
            customer.addPurchase(album);

            NotificationInterested notifyInterested = new NotificationInterested(customers, album, customer);
            notifyInterested.notificationAllInterestedCustomers();

        } else {
            System.out.println("Out of stock!");
        }
    }

    public boolean validatePurchase(Customer customer, Album album) {
        // Check stock
        if (album.getStock() <= 0) {
            System.out.println("Validation failed: Out of stock");
            return false;
        }

        // Check customer credit
        if (customer.getCredit() < album.getPrice()) {
            System.out.println("Validation failed: Insufficient credit");
            return false;
        }

        // Check age restriction for explicit content
        if (album.getAgeRestriction().equals(AgeRestriction.PARENTAL_ADVISORY) && customer.getDateOfBirth().isAfter(LocalDate.now().minusYears(18))) {
            System.out.println("Validation failed: Age restriction");
            return false;
        }

        return true;
    }

    public List<Album> getInventory() {
        return inventory;
    }

}
