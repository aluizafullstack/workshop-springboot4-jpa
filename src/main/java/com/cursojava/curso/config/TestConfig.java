package com.cursojava.curso.config;

import com.cursojava.curso.entities.User;
import com.cursojava.curso.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration // falando que é uma configuração
@Profile("test") // especificando que é uma classe de configuração do perfil de test -> application.properties -> spring.profiles.active=test

public class TestConfig implements CommandLineRunner { // vai exucutar quando o programfor iniciado -> implements CommandLineRunner
    // dependencia de objetos
    @Autowired // o spring vai resolver essa dependência e associar a instância userRepository
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        // para salvar uma lista de dados
        userRepository.saveAll(Arrays.asList(u1, u2));

    }
}
