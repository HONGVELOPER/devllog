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
        List<ImageEntity> imageEntityList = imageRequestDto.toEntity();
        imageRepository.saveAll(imageEntityList).forEach(savedImageEntity -> {
            imageResponseDtoList.add(new ImageResponseDto(savedImageEntity));
        });
        return imageResponseDtoList;
    }

    @Transactional
    public void delete(List<String> fileNames) {
        fileNames.forEach(imageRepository::deleteByImg); // 자바 람다 함수
//        fileNames.forEach(fileName -> {
//            imageRepository.deleteByImg(fileName);
//        });
    }

}
