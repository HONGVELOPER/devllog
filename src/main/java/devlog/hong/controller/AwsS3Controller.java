package devlog.hong.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import devlog.hong.controller.result.BaseResult;
import devlog.hong.controller.result.ListResult;
import devlog.hong.dto.S3RequestDto;
import devlog.hong.service.AwsS3Service;
import devlog.hong.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/s3")
@RequiredArgsConstructor
@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
public class AwsS3Controller {

    private final AwsS3Service awsS3Service;
    private final ResponseService responseService;

    @PostMapping("/images")
    public ListResult<String[]> save(@RequestPart List<MultipartFile> multipartFileList) {
        return responseService.getListResult(awsS3Service.save(multipartFileList));
    }

    @DeleteMapping("/images")
    public BaseResult delete(@RequestBody S3RequestDto s3RequestDto) {
        for (String a: s3RequestDto.getDeleteFileNameList()) {
            System.out.println(a.toString() + " string check");
        }
        awsS3Service.delete(s3RequestDto.getDeleteFileNameList());
        return responseService.getSuccessResult();
    }
}
