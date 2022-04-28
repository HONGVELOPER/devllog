package devlog.hong.service;

import devlog.hong.domain.entity.ImageEntity;
import devlog.hong.domain.entity.PostEntity;
import devlog.hong.repository.ImageRepository;
import devlog.hong.repository.PostRepository;
import devlog.hong.domain.dto.PostRequestDto;
import devlog.hong.domain.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ImageRepository imageRepository;

    @Transactional(readOnly = true)
    public List<PostResponseDto> findAll() {
        return  postRepository.findAll()
            .stream()
            .map(PostResponseDto::new)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(int id) throws EntityNotFoundException {
        PostEntity post = postRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("해당 id 에 대한 데이터가 없습니다."));
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto save(PostRequestDto postRequestDto) {
        System.out.println("SERVICE SAVE 진입");
        if (!postRequestDto.getImages().isEmpty()) {
            List<ImageEntity> images = postRequestDto.getImages();
            for (ImageEntity image: images) {
                System.out.println("image : " + image);
            }
        }
        // 스프링 이미지 먼저 S3 연결 후, DB 올린 ID 저장으로 바꿔야함
//            if (!postReqDto.getImages().isEmpty()) { // 값이 있을경우에만
//                postEntity.setImages(postReqDto.getImages());
//            }
        PostEntity savedPostEntity = postRepository.save(postRequestDto.toEntity());
        System.out.println("saved : " + savedPostEntity.toString());
        return new PostResponseDto(savedPostEntity);
    }

    @Transactional
    public PostResponseDto update(int id, PostRequestDto postRequestDto) {
        System.out.println(postRequestDto.toString());
        PostEntity originPost = postRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("해당" + id + "에 대한 데이터가 없습니다."));
        originPost.update(postRequestDto.getTitle(), postRequestDto.getContent(), postRequestDto.getAuthor(), postRequestDto.getThumbNail());
        PostEntity modifyPost = postRepository.save(originPost);
        // save 하지 않아도 더티 체킹으로 인하여 저장이 되지만, lastmodifiedDate 가 업데이트 안됌.
        return new PostResponseDto(modifyPost);
    }

    @Transactional
    public void delete(int id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public boolean password(String password) {
        System.out.println("pass : " + password);
        // password 가 0725 가 아니라 password==0725 로 인식됨 수정해야함
        return password.equals("0725");
    }
}




