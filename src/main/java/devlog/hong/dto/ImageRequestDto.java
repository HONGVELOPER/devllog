package devlog.hong.dto;

import devlog.hong.domain.entity.ImageEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ImageRequestDto {
    @NotBlank(message = "img 를 확인해주세요.")
    private List<String> images;

    @Builder
    public ImageRequestDto(List<String> images) {
        this.images = images;
    }

    public List<ImageEntity> toEntity() {
        List<ImageEntity> imageEntityList = new ArrayList<>();
        images.forEach(image -> {
            imageEntityList.add(ImageEntity.builder()
                    .img(image)
                    .build());
        });
        return imageEntityList;
    }
}
