package com.app.infrastructure;

import com.app.api.dto.AutorDto;
import com.app.api.dto.ComentarioDto;
import com.app.infrastructure.repository.PostRepository;
import com.app.infrastructure.repository.UserRepository;
import com.app.infrastructure.repository.entity.PostEntity;
import com.app.infrastructure.repository.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instanciation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired // injecting the PostRepository to handle post entities
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("gnew York"));

        // Clear existing data
       // userRepository.deleteAll();
        //postRepository.deleteAll();

      /*  UserEntity maria = new UserEntity(null, "Maria Brown", "maria@gmail.com");
        UserEntity alex = new UserEntity(null, "Alex Green", "alex@gmail.com");
        UserEntity bob = new UserEntity(null, "Bob Grey", "bob@gmail.com");

        // Save users to the repository
        userRepository.saveAll(Arrays.asList(maria, alex, bob)); // salva os usuarios para depois apontar para os post de forma aninhada

        // Create posts associated with the users
        PostEntity post1 = new PostEntity(null, sdf.parse("21/03/2020"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AutorDto(maria));
        PostEntity post2 = new PostEntity(null, sdf.parse("10/05/2021"), "Bom dia", "Acordei feliz hoje!", new AutorDto(maria));

        ComentarioDto comentarioDto = new ComentarioDto("Boa viagem mano!", sdf.parse("21/03/2020"), new AutorDto(alex));
        ComentarioDto comentarioDto2 = new ComentarioDto("Aproveite!", sdf.parse("21/03/2020"), new AutorDto(bob));
        ComentarioDto commentarioDto3 = new ComentarioDto("Tenha um ótimo dia!", sdf.parse("10/05/2021"), new AutorDto(alex));

        post1.getComenatrios().addAll(Arrays.asList(comentarioDto, comentarioDto2)); // adiciona os comentarios ao post1
        post2.getComenatrios().addAll(Arrays.asList(commentarioDto3)); // adiciona o comentario ao post2

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPost().addAll(Arrays.asList(post1, post2));// adiciona os posts ao usuario maria

        userRepository.save(maria); // atualiza o usuario maria com os posts*/

    }
}
