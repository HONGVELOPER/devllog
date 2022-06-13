package devlog.hong.domain.repository;

import devlog.hong.domain.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, Integer> {

    TagEntity findByTag(String tag);
}
