package devlog.hong.dto;

import devlog.hong.domain.entity.ImageEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
public class ImageResponseDto {
    private int id;
    private String image;
    private String date;

    // ResponseDto는 DB 처리 한 결과 값을 가지고 사용하므로 굳이 builder 패턴 사용 안함
    public ImageResponseDto(ImageEntity image) {
        this.id = image.getId();
        this.image = image.getImage();
        this.date = image.getUpdatedDate();
    }
}
