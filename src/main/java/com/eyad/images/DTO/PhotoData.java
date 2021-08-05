package com.eyad.images.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoData {
    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;
}
