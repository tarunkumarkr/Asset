package com.tarunkumar.practice.Repo;

import com.tarunkumar.practice.Entity.Asset;
import com.tarunkumar.practice.Enum.AssetStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AssertRepo extends MongoRepository<Asset, String> {

    boolean existsByItemId(String itemId);

    Long deleteByItemId(String itemId);

    Optional<Asset> findByItemId(String itemId);

    List<Asset> findByItemIdAndLocation(String itemId, String location);

    List<Asset> findByStatusAndDepartment(AssetStatus status, String department);

}