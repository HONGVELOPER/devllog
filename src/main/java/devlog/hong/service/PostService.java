package devlog.hong.service;

import devlog.hong.domain.entity.PostEntity;
import devlog.hong.domain.repository.PostRepository;
import devlog.hong.domain.dto.PostReqDto;
import devlog.hong.domain.dto.PostResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostResDto getPost(int id) {
        Optional<PostEntity> post = postRepository.findById(id);
        return post.map(PostResDto::new).orElseThrow(
                () -> new EntityNotFoundException("해당 id 에 대한 데이터가 없습니다.")
        );
    }

    @Transactional // 예외 처리 해야함
    public PostResDto writePost(PostReqDto postReqDto) {
        // 스프링 이미지 먼저 S3 연결 후, DB 올린 ID 저장으로 바꿔야함
        PostEntity postEntity = PostEntity.builder()
                .title(postReqDto.getTitle())
                .content(postReqDto.getContent())
                .writer(postReqDto.getWriter())
                .viewCount(postReqDto.getViewCount())
                .thumbNail(postReqDto.getThumbNail())
                .build();
        System.out.println("entity : " + postEntity.toString());
//        if (!postReqDto.getImages().isEmpty()) {
//            postEntity.setImages(postReqDto.getImages());
//        }
//        PostEntity savedPostEntity = postRepository.save(postEntity);
//        System.out.println("saved : " + savedPostEntity.toString());
        return new PostResDto(postRepository.save(postEntity));
    }

    @Transactional
    public void updatePost(int id, PostReqDto postReqDto) {
        Optional<PostEntity> originPost = postRepository.findById(postReqDto.getId());
        if (originPost.isEmpty()) {
            throw new EntityNotFoundException("해당 id에 대한 데이터가 없습니다.");
        } else {
            PostEntity modifiedPost = originPost.get();
            if (postReqDto.getTitle() != null) {
                modifiedPost.setTitle(postReqDto.getTitle());
            } if (postReqDto.getContent() != null) {
                modifiedPost.setContent(postReqDto.getContent());
            } if (postReqDto.getWriter() != null) {
                modifiedPost.setWriter(postReqDto.getWriter());
            } if (postReqDto.getViewCount() != 0) {
                modifiedPost.setViewCount(postReqDto.getViewCount());
            } if (postReqDto.getThumbNail() != null) {
                modifiedPost.setThumbNail(postReqDto.getThumbNail());
            }
            postRepository.save(modifiedPost);
        }
    }

}
