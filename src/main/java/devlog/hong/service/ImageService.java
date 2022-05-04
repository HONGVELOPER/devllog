package devlog.hong.service;

import devlog.hong.domain.entity.ImageEntity;
import devlog.hong.domain.repository.ImageRepository;
import devlog.hong.dto.ImageRequestDto;
import devlog.hong.dto.ImageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    @Transactional
    public List<ImageResponseDto> save(ImageRequestDto imageRequestDto) {
        List<ImageResponseDto> imageResponseDtoList = new ArrayList<>();
        List<ImageEntity> imageEntities = imageRequestDto.toEntity();
        imageRepository.saveAll(imageEntities).forEach(savedImageEntity -> {
            imageResponseDtoList.add(new ImageResponseDto(savedImageEntity));
        });
        return imageResponseDtoList;
    }

}
