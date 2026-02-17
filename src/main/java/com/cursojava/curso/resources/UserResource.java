package com.cursojava.curso.resources;

import com.cursojava.curso.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// deixa explicito que é um controle
@RestController
// nomeando o recurso e o value informa o caminho
@RequestMapping(value = "/users")

public class UserResource {
    // retorna resposta de requisições WEB, sendo esse um tipo específico so SPRING
    //ok -> retorna resposta com sucesso
    // body -> retorna o corpo da resposta
    // @GetMapping -> para deixar explicito que responde o GET do HDDP
    @GetMapping
    public ResponseEntity<User> findAll() {
        User u = new User(1L, "Maria", "maria@gmail.com", "9999999", "1234");
        return ResponseEntity.ok().body(u);
    }

}
