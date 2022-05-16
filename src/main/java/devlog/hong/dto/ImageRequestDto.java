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

@Getter
@NoArgsConstructor
public class ImageRequestDto {

    @NotNull(message = "post id 를 확인해주세요.")
    private int postId;

    @Valid
    @NotNull(message = "img 를 확인해주세요.")
    @Size(min = 1)
    private List<String> images;

    @Builder
    public ImageRequestDto(int postId, List<String> images) {
        this.postId = postId;
        this.images = images;
    }

    public List<ImageEntity> toEntity(PostEntity postEntity) {
        List<ImageEntity> imageEntityList = new ArrayList<>();
        images.forEach(image -> {
            imageEntityList.add(ImageEntity.builder()
                            .postEntity(postEntity)
                            .image(image)
                            .build());
        });
        return imageEntityList;
    }
}
