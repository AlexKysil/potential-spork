package dal.repository;

import dal.models.Customer;
import dal.models.Product;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class iShopRepositoryImpl implements iShopRepository {
    @Override
    public Customer saveCustomer(Customer customer) {
        return null;
    }

    @Override
    public Optional<Customer> readCustomer(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public void deleteCustomer(UUID uuid) {

    }

    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public Optional<Product> readProduct(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public void deleteProduct(UUID uuid) {

    }
}
