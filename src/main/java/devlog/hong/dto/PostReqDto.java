package devlog.hong.dto;

import devlog.hong.domain.entity.PostEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@AllArgsConstructor
public class PostReqDto extends BaseDto {
    private String title;
    private String content;
    private String writer;
    private int viewCount;
    private String thumbNail;
}
