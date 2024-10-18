package com.example.music.repositories.mongodb;

import com.example.music.domain.Album;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoAlbumRepository extends MongoRepository<Album, String> {
}