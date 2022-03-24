package devlog.hong.dto;

import devlog.hong.domain.entity.PostEntity;
import lombok.Getter;

@Getter
public class PostResDto {
    private String title;
    private String content;
    private String writer;
    private int viewCount;
    private String thumbNail;

    public PostResDto(PostEntity post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.writer = post.getWriter();
        this.viewCount = post.getViewCount();
        this.thumbNail = post.getThumbNail();
    }
}
