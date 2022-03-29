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
    public Map<String, Object> post(@RequestBody PostReqDto postReqDto) {
        Map<String, Object> response = new HashMap<>();
        System.out.println(postReqDto.toString());
        PostResDto postResDto = postService.writePost(postReqDto);
        if (postResDto != null) {
            response.put("result", "SUCCESS");
            response.put("post", postResDto);
        }
        return response;
    }

    @PatchMapping("/update/{id}")
    public Map<String, Object> update(@PathVariable("id") int id, @RequestBody PostReqDto postReqDto) {
        Map<String, Object> response = new HashMap<>();
        postReqDto.setId(id);
        System.out.println(postReqDto.toString());
        postService.updatePost(id ,postReqDto);
        return response;
    }

//    @DeleteMapping("/delete")
//    public
}
