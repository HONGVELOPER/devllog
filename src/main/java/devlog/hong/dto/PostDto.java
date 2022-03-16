package devlog.hong.dto;

import devlog.hong.domain.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
public class PostDto extends BaseDto {
    private int id;
    private String title;
    private String content;
    private String writer;
    private int viewCount;
    private String thumbNail;
}
