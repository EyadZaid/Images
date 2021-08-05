package com.eyad.images.service;

import com.eyad.images.model.Photo;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface PhotosRepository extends CrudRepository<Photo, Integer>{
    List<Photo> findByAlbumId(int albumId);

    Photo findByAlbumIdAndPhotoId(int albumId, int photoId);
}
