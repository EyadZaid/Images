package com.eyad.images.controller;

import com.eyad.images.service.PhotoService;
import com.eyad.images.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @GetMapping("/all")
    public List<Photo> listAllPhotos() {
        return photoService.listAllPhotos();
    }

    @GetMapping("/album")
    public List<Photo> listPhotosByAlbumId(@RequestParam("albumId") int albumId) {
        return photoService.listPhotosByAlbumId(albumId);
    }

    @GetMapping("/photo")
    public HttpEntity<Resource> download(@RequestParam("albumId") int albumId, @RequestParam("photoId") int photoId) {
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new HttpEntity<>(photoService.getPhoto(albumId, photoId), headers);
        }
        catch(IOException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found", e);
        }

    }

}
