package devlog.hong.domain.dto;

import devlog.hong.domain.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostResDto extends BaseDto {
    private String title;
    private String content;
    private String writer;
    private int viewCount;
    private String thumbNail;

    @Builder
    public PostResDto(PostEntity post) {
        super();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.writer = post.getWriter();
        this.viewCount = post.getViewCount();
        this.thumbNail = post.getThumbNail();
    }
}
