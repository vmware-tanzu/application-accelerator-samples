package com.example.music.repositories.jpa;

import com.example.music.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAlbumRepository extends JpaRepository<Album, String> {
}
