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
import java.util.ArrayList;
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

    @Transactional(readOnly = true) // image 없이 포스팅만 가져옴.
    public List<PostResponseDto> findAll() {
        return  postRepository.findAll()
            .stream()
            .map(postEntity -> new PostResponseDto(postEntity).regex())
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true) // 게시글과 해당 게시글의 사진 모두 가져옴.
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
        // save 하지 않아도 더티 체킹으로 인하여 DB에 저장이 되지만, LastModifiedDate 업데이트 안됌.
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

    @Transactional // cascade 설정으로 게시글이 삭제되면 해당 게시글이 갖는 사진 또한 삭제.
    public List<String> delete(int postId) {
        PostEntity deleteEntity = postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("해당" + postId + "에 대한 데이터가 없습니다."));
        List<String> deleteFileNameList = new ArrayList<>();
        deleteFileNameList.add(deleteEntity.getThumbNail().split(".com/")[1]);
        if (!deleteEntity.getImageEntityList().isEmpty()) {
            deleteEntity.getImageEntityList()
                    .forEach(ImageEntity -> deleteFileNameList.add(ImageEntity.getImage().split(".com/")[1]));
        }
        postRepository.deleteById(postId);
        return deleteFileNameList;
    }

    @Transactional
    public boolean verifyPassword(String password) {
        return password.equals(envPassword);
    }

    @Transactional
    public void updateViewCount(int postId) {
        postRepository.updateViewCount(postId);
    }

}




