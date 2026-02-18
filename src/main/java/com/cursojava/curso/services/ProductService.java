package com.cursojava.curso.services;

import com.cursojava.curso.entities.Product;
import com.cursojava.curso.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // registra que a classe é um componente e com o @Autowired na classe UserResource...realmente vai ser injetado automaticamente
public class ProductService {
    @Autowired // o spring vai resolver essa dependência e associar a instância userRepository
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        //Retorna um objeto opcional
        Optional<Product> obj = repository.findById(id);
        return obj.get(); // get: retorna um objeto do tipo User que estiver dentro do Optional
    }


}
