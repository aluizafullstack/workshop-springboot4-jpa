package com.cursojava.curso.resources;

import com.cursojava.curso.entities.Order;
import com.cursojava.curso.services.OrderService;
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
@RequestMapping(value = "/orders")

public class OrderResource {
    @Autowired // o spring vai resolver essa dependência e associar a instância userRepository
    private OrderService service;

    // retorna resposta de requisições WEB, sendo esse um tipo específico so SPRING
    //ok -> retorna resposta com sucesso
    // body -> retorna o corpo da resposta
    // @GetMapping -> para deixar explicito que responde o GET do HTTP
    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // @GetMapping -> para deixar explicito que responde o GET do HTTP
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) { // @PathVariable para aceitar o id e usar como parametro
        Order obj = service.findById(id);
        //ok -> retorna resposta com sucesso
        // body -> retorna o corpo da resposta
        return ResponseEntity.ok().body(obj);
    }

}
