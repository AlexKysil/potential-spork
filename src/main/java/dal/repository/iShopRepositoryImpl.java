package dal.repository;

import dal.models.Customer;
import dal.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

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
            System.out.println("Customer entity was deleted"); // TODO replace syout with logger once the logger added
        }
    }

    @Override
    public Customer updateCustomer(UUID id, Map<String, Object> updates) {
        Customer customer = entityManager.find(Customer.class, id);
        if (customer == null) {
            throw new EntityNotFoundException("Customer not found");
        }

        updates.forEach((field, value) -> {
            try {
                Field declaredField = Customer.class.getDeclaredField(field);
                declaredField.setAccessible(true);
                declaredField.set(customer, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Failed to update field: " + field, e);
            }
        });

        entityManager.merge(customer);
        return customer;
    }

    @Override
    public List<Customer> searchCustomers(String firstName, String lastName, String email, String postcode) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);

        List<Predicate> predicates = new ArrayList<>();

        if (firstName != null && !firstName.isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%"));
        }
        if (lastName != null && !lastName.isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%"));
        }
        if (email != null && !email.isEmpty()) {
            predicates.add(cb.equal(cb.lower(root.get("email")), email.toLowerCase()));
        }
        if (postcode != null && !postcode.isEmpty()) {
            predicates.add(cb.equal(root.get("postcode"), postcode));
        }

        query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));
        return entityManager.createQuery(query).getResultList();
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
            System.out.println("Product entity was deleted"); // TODO replace syout with logger once the logger added
        }
    }
}
