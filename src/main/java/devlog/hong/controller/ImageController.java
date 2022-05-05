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

    @DeleteMapping("/partOfImages") // UPDATE 하면서 전체가 아닌 일부 사진 삭제
    public void deleteByName(@RequestBody List<String> fileNames) {
        awsS3Service.delete(fileNames);
        imageService.delete(fileNames);
    }

    @DeleteMapping("images") // 게시글이 삭제되면서 해당 글에 있는 모든 사진 Cascade 삭제
    public void deleteByPost(@RequestBody List<String> fileNames) {
        awsS3Service.delete(fileNames);
    }
}
