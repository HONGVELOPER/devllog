package devlog.hong.controller;

import devlog.hong.controller.result.ListResult;
import devlog.hong.dto.ImageRequestDto;
import devlog.hong.dto.ImageResponseDto;
import devlog.hong.service.AwsS3Service;
import devlog.hong.service.ImageService;
import devlog.hong.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ImageController {

    private final ResponseService responseService;
    private final AwsS3Service awsS3Service;
    private final ImageService imageService;

    @PostMapping("/images")
    public ListResult<ImageResponseDto> save(@RequestPart List<MultipartFile> multipartFiles) {
        System.out.println("image 접근");
        List<String> fileNames = awsS3Service.save(multipartFiles);
        return responseService.getListResult(imageService.save(new ImageRequestDto(fileNames)));
    }

    @DeleteMapping("images")
    public void delete(@PathVariable("postId") int postId, @RequestBody List<String> fileNames) {
        awsS3Service.delete(fileNames);
//        imageService.delete(postId); // cascade 로 부모 삭제시 자식 삭제되게 해야겟다.
    }
}
