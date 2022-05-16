package devlog.hong.service;

import devlog.hong.domain.entity.ImageEntity;
import devlog.hong.domain.entity.PostEntity;
import devlog.hong.domain.repository.ImageRepository;
import devlog.hong.domain.repository.PostRepository;
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
    public PostEntity save(PostRequestDto postRequestDto) {
        return postRepository.save(postRequestDto.toEntity());
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findAll() {
        return  postRepository.findAll()
            .stream()
            .map(PostResponseDto::new)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(int postId) throws EntityNotFoundException {
        PostEntity post = postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("해당" + postId + "에 대한 데이터가 없습니다.")
        );
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto update(int postId, PostRequestDto postRequestDto) {
        System.out.println(postRequestDto.toString());
        PostEntity originPost = postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("해당" + postId + "에 대한 데이터가 없습니다."));
        originPost.update(postRequestDto.getTitle(), postRequestDto.getContent(), postRequestDto.getAuthor(), postRequestDto.getThumbNail());
        // save 하지 않아도 더티 체킹으로 인하여 DB에 저장이 되지만, lastmodifiedDate 업데이트 안됌.
        return new PostResponseDto(postRepository.save(originPost));
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




