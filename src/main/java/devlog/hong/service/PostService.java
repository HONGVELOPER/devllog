package devlog.hong.service;

import devlog.hong.domain.entity.PostEntity;
import devlog.hong.domain.repository.PostRepository;
import devlog.hong.dto.PostReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostEntity getPost() {
        Optional<PostEntity> post = postRepository.findById(1);
        return post.get();
    }

    @Transactional
    public void writePost(PostReqDto postReqDto) throws Exception {
        PostEntity postEntity = new PostEntity(postReqDto);
        postRepository.save(postEntity);
    }

}
