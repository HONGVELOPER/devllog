package devlog.hong.domain.repository;

import devlog.hong.domain.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {

    void deleteByImage(String fileName);
}
