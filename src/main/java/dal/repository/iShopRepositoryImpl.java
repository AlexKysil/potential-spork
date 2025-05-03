package dal.repository;

import dal.models.Customer;
import dal.models.Product;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class iShopRepositoryImpl implements iShopRepository {

    private EntityManager entityManager;

    @Override
    public Customer saveCustomer(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }

    @Override
    public Optional<Customer> readCustomer(UUID uuid) {
        return Optional.of(entityManager.find(Customer.class, uuid));
    }

    @Override
    public void deleteCustomer(UUID uuid) {
        val customer2Delete = readCustomer(uuid);

        if(customer2Delete.isPresent()) {
            entityManager.remove(customer2Delete.get());
            System.out.println("Customer entity was deleted"); // TODO replace syout with logget once the logger added
        }
    }

    @Override
    public Product saveProduct(Product product) {
        entityManager.persist(product);
        return product;
    }

    @Override
    public Optional<Product> readProduct(UUID uuid) {
        return Optional.of(entityManager.find(Product.class, uuid));
    }

    @Override
    public void deleteProduct(UUID uuid) {
        val product2Delete = readProduct(uuid);

        if(product2Delete.isPresent()) {
            entityManager.remove(product2Delete.get());
            System.out.println("Product entity was deleted"); // TODO replace syout with logget once the logger added
        }
    }
}
