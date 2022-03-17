package devlog.hong.controller;

import devlog.hong.domain.entity.PostEntity;
import devlog.hong.dto.PostDto;
import devlog.hong.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("")
    public String hello() {
        System.out.println("hello 진입");
        return "hello world";
    }

    @GetMapping("/id1")
    public PostEntity no() {
        return postService.getPost();
    }
}
