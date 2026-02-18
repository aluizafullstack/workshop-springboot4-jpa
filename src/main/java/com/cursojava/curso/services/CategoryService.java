package com.cursojava.curso.services;

import com.cursojava.curso.entities.Category;
import com.cursojava.curso.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // registra que a classe é um componente e com o @Autowired na classe UserResource...realmente vai ser injetado automaticamente
public class CategoryService {
    @Autowired // o spring vai resolver essa dependência e associar a instância userRepository
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findById(Long id) {
        //Retorna um objeto opcional
        Optional<Category> obj = repository.findById(id);
        return obj.get(); // get: retorna um objeto do tipo User que estiver dentro do Optional
    }


}
