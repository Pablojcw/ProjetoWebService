package com.app.infrastructure.repository;

import com.app.infrastructure.repository.entity.PostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<PostEntity, String> {
    @Query(" { 'title': { $regex: ?0, $options: 'i' } }")
    List<PostEntity> suorcgTitle(String text);

    List<PostEntity> findByTitleContainingIgnoreCase(String text);// metodo para buscar posts por título, ignorando maiúsculas e minúsculas
    // i ignora maiúsculas     e minúsculas
    //?0 primeiro parametro
    // consulta para buscar posts por título, ignorando maiúsculas e minúsculas


}
