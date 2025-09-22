package dal.repository;

import dal.models.Customer;
import dal.models.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface iShopRepository {

    Customer saveCustomer(Customer customer);
    Optional<Customer> readCustomer(UUID uuid);
    void deleteCustomer(UUID uuid);
    Customer updateCustomer(UUID id, Map<String, Object> updates);
    List<Customer> searchCustomers(String firstName, String lastName, String email, String postcode);

Product saveProduct(Product product);
    Optional<Product> readProduct(UUID uuid);
    void deleteProduct(UUID uuid);
}
