package com.app.infrastructure.repository;

import com.app.infrastructure.repository.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    // static void savadAll(List<String> list) {

    //}
}
