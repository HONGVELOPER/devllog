package devlog.hong.service;

import devlog.hong.domain.entity.PostEntity;
import devlog.hong.dto.PostDto;
import devlog.hong.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public PostEntity getPost() {
        Optional<PostEntity> post = postRepository.findById(1);
        return post.get();
    }
}
