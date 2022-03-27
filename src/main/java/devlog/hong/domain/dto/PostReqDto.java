package devlog.hong.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostReqDto extends BaseDto {
    private String title;
    private String content;
    private String writer;
    private int viewCount;
    private String thumbNail;
}
