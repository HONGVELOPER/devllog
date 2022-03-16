package devlog.hong.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto extends CommonDto {
    private String title;
    private String content;
    private String writer;
    private int viewCount;
    private String thumbNail;
}
