package devlog.hong.domain.dto;

import devlog.hong.domain.entity.ImageEntity;
import devlog.hong.domain.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
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

    private List<ImageEntity> images;

    @Builder
    public PostRequestDto(String title, String content, String author, int viewCount, String thumbNail) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.viewCount = viewCount;
        this.thumbNail = thumbNail;
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
