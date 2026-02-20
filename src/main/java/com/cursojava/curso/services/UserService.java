package com.cursojava.curso.services;

import com.cursojava.curso.entities.User;
import com.cursojava.curso.repositories.UserRepository;
import com.cursojava.curso.services.exceptions.DatabaseException;
import com.cursojava.curso.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component; // mesma coisa que o Service, mas como a classe é um serviço, será usada o @Service
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // registra que a classe é um componente e com o @Autowired na classe UserResource...realmente vai ser injetado automaticamente
public class UserService {
    @Autowired // o spring vai resolver essa dependência e associar a instância userRepository
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        //Retorna um objeto opcional
        Optional<User> obj = repository.findById(id);
        // orElseThrow: ele tenta dar o get, mas se não encontrar um usuario esse dá a exeção
        return obj.orElseThrow(() -> new ResourceNotFoundException(id)); // get: retorna um objeto do tipo User que estiver dentro do Optional
    }

    public User insert(User obj) {
        return repository.save(obj);
    }

    public void delete (Long id) {
        try {
            repository.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id); // printStackTrace -> vê o tipo de exeção que foi lançado na tela (console),
            // não é mais possivel ver no Postmann, ai com isso foi colocado a exeção vista no catch
        } catch(DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage()); // exeção de serviço
        }
    }

    // getReferenceById -> esse metodo não vai diretamente no banco de dados, ele apenas prepara o obj para ser mexido e
    // depois efetuar alguma operação no banco de dados
    public User update(long id, User obj) {
        try {
            User entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}
