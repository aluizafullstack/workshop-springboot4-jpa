package com.cursojava.curso.resources;

import com.cursojava.curso.entities.User;
import com.cursojava.curso.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    // @GetMapping -> para deixar explicito que responde o GET do HTTP, recuperar dados
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

    // faz um pré-processamento na compilação do controlador, e definindo que esse
    // metodo PostMapping
    // @RequestBody ->para chegar no modo Json e esse Json vai ser descerealizado para o obj user
    @PostMapping
    public ResponseEntity<User> insert (@RequestBody User obj) {
        obj = service.insert(obj);
        //ok -> retorna resposta com sucesso
        // created -> no Postman tem que retornar o codigo 201, pois é um codigo especifico que diz que foi criado um nosso recurso
        // precisa desse URI
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    // anotação para deletar
    // está com void pporque não vai retornar nada
    // @PathVariable -> para ser reconhecido como uma variavel na URL
    // noContent() -> retorna uma resposta vazia
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    //@PuMapping -> para atualizar o banco de dados
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update (@PathVariable Long id, @RequestBody User obj) {
        obj= service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}