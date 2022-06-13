package devlog.hong.dto;

import devlog.hong.domain.entity.TagEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class TagRequestDto {

    private List<String> tags;

    @Builder
    public TagRequestDto(List<String> tags) {
        this.tags = tags;
    }

    public List<TagEntity> toEntity() {
        return this.tags.stream()
                .map(tag -> TagEntity.builder()
                    .tag(tag)
                    .build())
                .collect(Collectors.toList());
    }
}
