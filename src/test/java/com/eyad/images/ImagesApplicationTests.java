package com.eyad.images;

import com.eyad.images.initialize.InitializeApp;
import com.eyad.images.service.PhotosRepository;
import com.eyad.images.model.Photo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ImagesApplicationTests {

    @Autowired
    private PhotosRepository photosRepository;

    @Autowired
    private InitializeApp initializeApp;

    @Test
    public void photosRepositoryNullTest() {
        assertNotNull(photosRepository);
    }

    @Test
    public void photosRepositoryEmptyTest() {
        photosRepository.deleteAll();
        assertTrue(photosRepository.count() == 0);
    }

    @Test
    public void photosRepositoryInsertTest() {
        photosRepository.deleteAll();
        Photo photo = new Photo();
        photo.setAlbumId(15);
        photo.setPhotoId(33);
        photo.setTitle("New photo");
        photo.setDownloadDatetime(new Date());
        photo.setLocalPath("D:\\JavaCourse\\Shield\\Images\\src\\main\\resources\\photos\\15_33.jpeg");
        photo.setFileSize(15000);
        photosRepository.save(photo);
        assertTrue(photosRepository.count() == 1);
        photosRepository.deleteAll();
    }

    @Test
    public void photosRepositoryGetPhotoTest() {
        photosRepository.deleteAll();
        try {
            initializeApp.insertDataToDB();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(photosRepository.count() > 0);
        photosRepository.deleteAll();
    }

    @Test
    public void photosRepositoryFindByAlbumIdAndPhotoIdTest() {
        photosRepository.deleteAll();
        Photo photo = new Photo();
        photo.setAlbumId(15);
        photo.setPhotoId(33);
        photo.setTitle("New photo");
        photo.setDownloadDatetime(new Date());
        photo.setLocalPath("D:\\JavaCourse\\Shield\\Images\\src\\main\\resources\\photos\\15_33.jpeg");
        photo.setFileSize(15000);
        photosRepository.save(photo);
        assertEquals(photo.getPhotoId(), photosRepository.findByAlbumIdAndPhotoId(15,33).getPhotoId());
        photosRepository.deleteAll();
    }

}
