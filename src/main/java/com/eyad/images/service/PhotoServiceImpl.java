package com.eyad.images.service;

import com.eyad.images.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotosRepository photosRepository;

    @Override
    public List<Photo> listAllPhotos() {
        return (List<Photo>) photosRepository.findAll();
    }

    @Override
    public List<Photo> listPhotosByAlbumId(int albumId) {
        return photosRepository.findByAlbumId(albumId);
    }

    @Override
    public Resource getPhoto(int albumId, int photoId) throws FileNotFoundException, MalformedURLException {
        Photo photo = photosRepository.findByAlbumIdAndPhotoId(albumId, photoId);
        if(photo != null){
            String fileName = photo.getAlbumId() + "_" + photo.getPhotoId() + ".jpeg";
            Path root = Paths.get("src", "main", "resources", "photos");
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException();
            }
        }
        else{
            throw new FileNotFoundException();
        }
    }
}
