package devlog.hong.controller;

import devlog.hong.controller.result.BaseResult;
import devlog.hong.controller.result.ListResult;
import devlog.hong.dto.ImageRequestDto;
import devlog.hong.dto.ImageResponseDto;
import devlog.hong.service.ImageService;
import devlog.hong.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final ResponseService responseService;

    @PostMapping("/images/{postId}")
    public ListResult<ImageResponseDto> save(
            @PathVariable("postId") int postId,
            @RequestBody @Valid ImageRequestDto imageRequestDto) {
        return responseService.getListResult(imageService.save(postId, imageRequestDto));
    }

    @DeleteMapping("/images")
    public BaseResult deleteByName(@RequestBody @Valid ImageRequestDto imageRequestDto) {
        imageService.delete(imageRequestDto.getDeleteImages());
        return responseService.getSuccessResult();
    }
}
