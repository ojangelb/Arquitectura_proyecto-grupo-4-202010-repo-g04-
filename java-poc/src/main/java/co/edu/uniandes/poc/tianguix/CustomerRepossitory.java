package co.edu.uniandes.poc.tianguix;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepossitory extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    Customer findById(long id);
}