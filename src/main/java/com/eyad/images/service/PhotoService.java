package com.eyad.images.service;

import com.eyad.images.model.Photo;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

public interface PhotoService {
    List<Photo> listAllPhotos();

    List<Photo> listPhotosByAlbumId(int albumId);

    Resource getPhoto(int albumId, int photoId) throws FileNotFoundException, MalformedURLException;
}
