package devlog.hong.controller;

import devlog.hong.domain.dto.PostReqDto;
import devlog.hong.domain.dto.PostResDto;
import devlog.hong.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/{id}")
    public PostResDto get(@PathVariable("id") int id) {
        return postService.getPost(id);
    }

    @PostMapping("/write")
    public void post(@RequestBody PostReqDto postReqDto) {
        System.out.println(postReqDto.toString());
        postService.writePost(postReqDto);
    }

    @PatchMapping("/update/{id}")
    public Map<String, Object> update(@PathVariable("id") int id, @RequestBody PostReqDto postReqDto) {
        Map<String, Object> response = new HashMap<>();
        postReqDto.setId(id);
        System.out.println(postReqDto.toString());
        postService.updatePost(postReqDto);
        return response;
    }
}
