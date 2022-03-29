package devlog.hong.domain.dto;

import devlog.hong.domain.entity.ImageEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PostReqDto extends BaseDto {
    private String title;
    private String content;
    private String writer;
    private int viewCount;
    private String thumbNail;
    private List<ImageEntity> images;
}
