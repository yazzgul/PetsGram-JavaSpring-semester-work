package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.itis.model.Image;
import ru.itis.repository.ImageRepository;
import ru.itis.repository.PostRepository;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Cacheable("images")
    public Image findImageById(Long id) {
        Image image = imageRepository.findById(id).orElse(null);
        return image;
    }
}
