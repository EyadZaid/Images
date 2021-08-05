package com.eyad.images.initialize;

import com.eyad.images.DTO.PhotoData;
import com.eyad.images.service.ImportPhotosData;
import com.eyad.images.mapper.PhotoDataMapper;
import com.eyad.images.service.PhotosRepository;
import com.eyad.images.model.Photo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class InitializeApp {

    @Value("${downloaded.photos.path}")
    private String downloadedPhotosPath;

    @Autowired
    private PhotosRepository photosRepository;

    @Autowired
    private ImportPhotosData importPhotosData;

    @Autowired
    private PhotoDataMapper photoDataMapper;

    @PostConstruct
    public void insertDataToDB() throws IOException {
        if(photosRepository.count() == 0){
            List<PhotoData> photoDataList = getPhotosList();
            List<Photo> photosList = downloadPhotosToLocalFolder(photoDataList);
            photosRepository.saveAll(photosList);
        }
    }

    private List<PhotoData> getPhotosList() throws JsonProcessingException {
        String json = importPhotosData.jsonGetRequest();
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(json, PhotoData[].class));
    }

    private List<Photo> downloadPhotosToLocalFolder(List<PhotoData> photoDataList) throws IOException {
        List<Photo> photosList = new ArrayList<>();
        for (var photoData : photoDataList) {
            try {
                File photoFile = downloadPhoto(photoData);
                photosList.add(photoDataMapper.photoDataToEntity(photoData, photoFile));
            }
            catch(Exception e){
                throw e;
            }
        }
        return photosList;
    }

    private File downloadPhoto(PhotoData photoData) throws IOException {
        BufferedImage bufferedFile;
        URL url = new URL(photoData.getUrl());
        bufferedFile = ImageIO.read(url);
        File photoFile = new File(downloadedPhotosPath + photoData.getAlbumId() + "_" +
                photoData.getId() + ".jpeg");
        ImageIO.write(bufferedFile, "jpeg", photoFile);
        return photoFile;
    }

}
