package dal.repository;

import dal.models.Customer;
import dal.models.Product;

import java.util.Optional;
import java.util.UUID;

public interface iShopRepository {

    Customer saveCustomer(Customer customer);
    Optional<Customer> readCustomer(UUID uuid);
    void deleteCustomer(UUID uuid);

    Product saveProduct(Product product);
    Optional<Product> readProduct(UUID uuid);
    void deleteProduct(UUID uuid);
}
