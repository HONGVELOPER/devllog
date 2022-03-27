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

    @Transactional
    public void writePost(PostReqDto postReqDto) {
        PostEntity postEntity = new PostEntity(postReqDto);
        postRepository.save(postEntity);
    }

    @Transactional
    public void updatePost(PostReqDto postReqDto) {
        System.out.println("id : " + postReqDto.getId());
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
