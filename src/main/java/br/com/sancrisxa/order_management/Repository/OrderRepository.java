package br.com.sancrisxa.order_management.Repository;

import br.com.sancrisxa.order_management.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
