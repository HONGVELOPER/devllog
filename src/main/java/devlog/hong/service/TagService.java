package devlog.hong.service;

import devlog.hong.domain.entity.TagEntity;
import devlog.hong.domain.repository.TagRepository;
import devlog.hong.dto.TagRequestDto;
import devlog.hong.dto.TagResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    @Transactional(readOnly = true)
    public List<TagEntity> save(TagRequestDto tagRequestDto) {
        List<TagEntity> tagEntityList = tagRequestDto.toEntity();
        // 중복 태그 제거
        tagEntityList.stream()
                .filter(tagEntity -> tagRepository.findByTag(tagEntity.getTag()).getTag().isEmpty())
                .collect(Collectors.toList());
        return tagRepository.saveAll(tagEntityList);
    }

    @Transactional(readOnly = true)
    public List<TagResponseDto> findAll() {
        return tagRepository.findAll()
                .stream()
                .map(TagResponseDto::new)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public void delete (int tagId) {
        TagEntity tagEntity = tagRepository.findById(tagId).orElseThrow(
                () -> new EntityNotFoundException("해당 id에 대한 데이터를 찾을 수 없습니다."));
        tagRepository.deleteById(tagEntity.getId());
    }
}
