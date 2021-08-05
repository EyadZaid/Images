package com.eyad.images.mapper;

import com.eyad.images.DTO.PhotoData;
import com.eyad.images.model.Photo;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;

@Component
public class PhotoDataMapper {

    public Photo photoDataToEntity(PhotoData photoData, File photoFile) {
        Photo photo = new Photo();
        photo.setPhotoId(photoData.getId());
        photo.setAlbumId(photoData.getAlbumId());
        photo.setTitle(photoData.getTitle());
        photo.setDownloadDatetime(new Date());
        photo.setLocalPath(photoFile.getAbsolutePath());
        photo.setFileSize(photoFile.length());
        return photo;
    }
}
