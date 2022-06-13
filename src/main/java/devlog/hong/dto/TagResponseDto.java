package devlog.hong.dto;

import devlog.hong.domain.entity.TagEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TagResponseDto {
    private int id;
    private String tag;

    public TagResponseDto(TagEntity tagEntity) {
        this.tag = tagEntity.getTag();
    }
}
