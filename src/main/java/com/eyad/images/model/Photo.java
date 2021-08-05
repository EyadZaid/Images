package com.eyad.images.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="photos")
public class Photo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int photoId;
    private int albumId;
    private String title;
    private Date downloadDatetime;
    private String localPath;
    private long fileSize;
}
