package io.github.abbassizied.sms.services;

import java.util.List;

import io.github.abbassizied.sms.entities.Image;

public interface ImageService {

    List<Image> listImages();

    Image getImageById(long id);

    Image saveImage(Image image);

    void deleteImageById(long id);
}
