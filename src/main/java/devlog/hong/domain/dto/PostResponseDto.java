package devlog.hong.domain.dto;

import devlog.hong.domain.entity.PostEntity;
import lombok.*;

import java.time.LocalDateTime;

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
