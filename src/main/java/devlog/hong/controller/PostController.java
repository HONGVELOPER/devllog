package devlog.hong.controller;

import devlog.hong.domain.entity.PostEntity;
import devlog.hong.dto.PostReqDto;
import devlog.hong.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/write")
    public void post(@RequestBody PostReqDto postReqDto) throws Exception {
        System.out.println(postReqDto.toString());
        postService.writePost(postReqDto);

//        requestData.forEach((key, value) -> {
//            System.out.println("key :" + key);
//            System.out.println("value :" + value);
//        });
    }
}
