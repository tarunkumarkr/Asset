package com.tarunkumar.practice.Repo;

import com.tarunkumar.practice.Entity.Asset;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AsserRepo extends MongoRepository<Asset, String> {

    boolean existsByItemId(String itemId);

    Long deleteByItemId(String itemId);

    Optional<Asset> findByItemId(String itemId);

}
