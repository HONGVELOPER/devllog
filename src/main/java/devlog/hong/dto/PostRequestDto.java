package devlog.hong.dto;

import devlog.hong.domain.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class PostRequestDto extends BaseDto {

    @NotBlank(message = "title 을 확인해주세요.")
    private String title;

    @NotBlank(message = "content 을 확인해주세요.")
    private String content;

    @NotBlank(message = "작성자 을 확인해주세요.")
    private String author;

    @NotNull
    private int viewCount;

    @NotBlank(message = "thumbNail 을 확인해주세요.")
    private String thumbNail;

    // 포스팅은 이미지가 있을수도 없을수도 있다.
    private List<String> images;

    private List<String> deleteImages;

    @Builder
    public PostRequestDto(String title, String content, String author, int viewCount, String thumbNail, List<String> images, List<String> deleteImages) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.viewCount = viewCount;
        this.thumbNail = thumbNail;
        this.images = images;
        this.deleteImages = deleteImages;
    }

    public PostEntity toEntity() {
        return PostEntity.builder()
                .title(title)
                .content(content)
                .author(author)
                .viewCount(viewCount)
                .thumbNail(thumbNail)
                .build();
    }
}
