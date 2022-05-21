package devlog.hong.dto;

import devlog.hong.domain.entity.PostEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@ToString
public class PostResponseDto {

    private int id;
    private String title;
    private String content;
    private String author;
    private int viewCount;
    private String thumbNail;
    private LocalDateTime date;
    private List<ImageResponseDto> imageResponseDtoList;

    public void setImageResponseDtoList(List<ImageResponseDto> imageResponseDtoList) {
        this.imageResponseDtoList = imageResponseDtoList;
    }

    public PostResponseDto(PostEntity post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.viewCount = post.getViewCount();
        this.thumbNail = post.getThumbNail();
        this.date = post.getModifiedDate();
    }
}
