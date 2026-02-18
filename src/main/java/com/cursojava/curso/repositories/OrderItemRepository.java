package com.cursojava.curso.repositories;

import com.cursojava.curso.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

// Nesse caso não será necessário fazer as implementações, porque o Spring da JPA já tem uma implementação padrão
// Se o JpaRepository for genericoe usando a entidade criada por você e o tipo do Id da entidade, já tem uma implementação padrão
// @Repository poderia ser usado aqui, mas o extends JpaRepository já herda que é um componente do Spring

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
