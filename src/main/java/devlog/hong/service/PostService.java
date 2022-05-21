package devlog.hong.service;

import devlog.hong.domain.entity.ImageEntity;
import devlog.hong.domain.entity.PostEntity;
import devlog.hong.domain.repository.PostRepository;
import devlog.hong.dto.ImageResponseDto;
import devlog.hong.dto.PostRequestDto;
import devlog.hong.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Value("${verify.password}")
    private String envPassword;

    @Transactional
    public PostResponseDto save(PostRequestDto postRequestDto) {
        PostEntity postEntity = postRequestDto.toEntity();
        if (!postRequestDto.getImages().isEmpty()) {
            postRequestDto.getImages().stream()
                    .map(image -> ImageEntity.builder()
                                    .postEntity(postEntity)
                                    .image(image)
                                    .build())
                    .forEach(postEntity::addImageEntity);
        }
        PostEntity savedPostEntity = postRepository.save(postEntity);
        PostResponseDto postResponseDto = new PostResponseDto(savedPostEntity);
        if (!savedPostEntity.getImageEntityList().isEmpty()) {
            List<ImageResponseDto> imageResponseDtoList = savedPostEntity.getImageEntityList()
                    .stream()
                    .map(ImageResponseDto::new)
                    .collect(Collectors.toList());
            postResponseDto.setImageResponseDtoList(imageResponseDtoList);
        }
        return postResponseDto;
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findAll() {
        return  postRepository.findAll()
            .stream()
            .map(PostResponseDto::new)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(int postId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("해당" + postId + "에 대한 데이터가 없습니다.")
        );
        PostResponseDto postResponseDto = new PostResponseDto(postEntity);
        if (!postEntity.getImageEntityList().isEmpty()) {
            List<ImageResponseDto> imageResponseDtoList = postEntity.getImageEntityList()
                    .stream()
                    .map(ImageResponseDto::new)
                    .collect(Collectors.toList());
            postResponseDto.setImageResponseDtoList(imageResponseDtoList);
        }
        return postResponseDto;
    }

    @Transactional
    public PostResponseDto update(int postId, PostRequestDto postRequestDto) {
        System.out.println(postRequestDto.toString());
        PostEntity originPostEntity = postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("해당" + postId + "에 대한 데이터가 없습니다."));
        originPostEntity.update(postRequestDto.getTitle(), postRequestDto.getContent(), postRequestDto.getAuthor(), postRequestDto.getThumbNail());
        if (!postRequestDto.getImages().isEmpty()) {
            postRequestDto.getImages().stream()
                    .map(image -> ImageEntity.builder()
                            .postEntity(originPostEntity)
                            .image(image)
                            .build())
                    .forEach(originPostEntity::addImageEntity);
        }
        // save 하지 않아도 더티 체킹으로 인하여 DB에 저장이 되지만, lastmodifiedDate 업데이트 안됌.
        PostEntity savedPostEntity = postRepository.save(originPostEntity);
        PostResponseDto postResponseDto = new PostResponseDto(savedPostEntity);
        if (!savedPostEntity.getImageEntityList().isEmpty()) {
            List<ImageResponseDto> imageResponseDtoList = savedPostEntity.getImageEntityList()
                    .stream()
                    .map(ImageResponseDto::new)
                    .collect(Collectors.toList());
            postResponseDto.setImageResponseDtoList(imageResponseDtoList);
        }
        return postResponseDto;
    }


    @Transactional
    public void delete(int postId) {
        postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("해당" + postId + "에 대한 데이터가 없습니다."));
        postRepository.deleteById(postId);
    }

    @Transactional
    public boolean verifyPassword(String password) {
        return password.equals(envPassword);
    }
}




