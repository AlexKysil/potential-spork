package dal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import java.util.UUID;

@Entity
@Table(name="product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    UUID id;

    @Column(name = "name")
    String name;

    @Column(name = "category")
    String category;

    @Column(name = "count")
    Integer count;

    @Column(name = "price")
    Double price;

    @Column(name = "description")
    String description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;
}
