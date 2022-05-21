package devlog.hong.dto;

import devlog.hong.domain.entity.ImageEntity;
import devlog.hong.domain.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ImageRequestDto {

    private List<String> images;

    private List<String> deleteImages;

    @Builder
    public ImageRequestDto(int postId, List<String> images, List<String> deleteImages) {
        this.images = images;
        this.deleteImages = deleteImages;
    }

    public List<ImageEntity> toEntity(PostEntity postEntity) {
        return this.images.stream()
                .map(image -> ImageEntity.builder()
                .postEntity(postEntity)
                .image(image)
                .build())
                .collect(Collectors.toList());
    }
}
