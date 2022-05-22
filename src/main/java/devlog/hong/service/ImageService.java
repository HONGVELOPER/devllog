package devlog.hong.service;

import devlog.hong.domain.entity.ImageEntity;
import devlog.hong.domain.entity.PostEntity;
import devlog.hong.domain.repository.ImageRepository;
import devlog.hong.domain.repository.PostRepository;
import devlog.hong.dto.ImageRequestDto;
import devlog.hong.dto.ImageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final PostRepository postRepository;

    // 이미지 S3 저장 후, image 저장해야함
    @Transactional
    public List<ImageResponseDto> save(int postId, ImageRequestDto imageRequestDto) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("해당" + postId + "에 대한 데이터가 없습니다.")
        );
        List<ImageEntity> imageEntityList = imageRequestDto.toEntity(postEntity);
        return imageRepository.saveAll(imageEntityList)
                .stream()
                .map(ImageResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(List<String> deleteImages) {
        deleteImages.forEach(imageRepository::deleteByImage);
    }

}
