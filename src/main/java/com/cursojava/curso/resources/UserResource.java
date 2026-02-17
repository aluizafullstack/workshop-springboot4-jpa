package com.cursojava.curso.resources;

import com.cursojava.curso.entities.User;
import com.cursojava.curso.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// deixa explicito que é um controle
@RestController
// nomeando o recurso e o value informa o caminho
@RequestMapping(value = "/users")

public class UserResource {
    @Autowired // o spring vai resolver essa dependência e associar a instância userRepository
    private UserService service;

    // retorna resposta de requisições WEB, sendo esse um tipo específico so SPRING
    //ok -> retorna resposta com sucesso
    // body -> retorna o corpo da resposta
    // @GetMapping -> para deixar explicito que responde o GET do HTTP
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // @GetMapping -> para deixar explicito que responde o GET do HTTP
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) { // @PathVariable para aceitar o id e usar como parametro
        User obj = service.findById(id);
        //ok -> retorna resposta com sucesso
        // body -> retorna o corpo da resposta
        return ResponseEntity.ok().body(obj);
    }

}
