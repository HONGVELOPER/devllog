package devlog.hong.controller;

import devlog.hong.controller.result.BaseResult;
import devlog.hong.controller.result.ListResult;
import devlog.hong.controller.result.SingleResult;
import devlog.hong.dto.PostRequestDto;
import devlog.hong.dto.PostResponseDto;
import devlog.hong.dto.PasswordRequestDto;
import devlog.hong.service.PostService;
import devlog.hong.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final ResponseService responseService;

    @GetMapping("/posts")
    public ListResult<PostResponseDto> findAll() {
        return responseService.getListResult(postService.findAll());
    }

    @GetMapping("/post/{postId}")
    public SingleResult<PostResponseDto> findById(@PathVariable("postId") int postId) {
        return responseService.getSingleResult(postService.findById(postId));
    }

    @PostMapping("/post")
    public SingleResult<PostResponseDto> save(@RequestBody @Valid PostRequestDto postRequestDto) {
        return responseService.getSingleResult(postService.save(postRequestDto));
    }

    @PatchMapping("/post/{postId}")
    public SingleResult<PostResponseDto> update(@PathVariable("postId") int postId, @RequestBody PostRequestDto postRequestDto) {
        postRequestDto.setId(postId);
        System.out.println(postRequestDto.toString());
        return responseService.getSingleResult(postService.update(postId, postRequestDto));
    }

    @DeleteMapping("/post/{postId}")
    public BaseResult delete(@PathVariable("postId") int postId) {
        postService.delete(postId);
        return responseService.getSuccessResult();
    }

    @PostMapping("/post/password")
    public BaseResult password(@RequestBody @Valid PasswordRequestDto passwordRequestDto) {
        boolean response = postService.verifyPassword(passwordRequestDto.getPassword());
        return response ? responseService.getSuccessResult() : responseService.getFailResult();
    }
}
