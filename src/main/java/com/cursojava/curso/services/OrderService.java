package com.cursojava.curso.services;

import com.cursojava.curso.entities.Order;
import com.cursojava.curso.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // registra que a classe é um componente e com o @Autowired na classe UserResource...realmente vai ser injetado automaticamente
public class OrderService {
    @Autowired // o spring vai resolver essa dependência e associar a instância userRepository
    private OrderRepository repository;

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order findById(Long id) {
        //Retorna um objeto opcional
        Optional<Order> obj = repository.findById(id);
        return obj.get(); // get: retorna um objeto do tipo User que estiver dentro do Optional
    }


}
