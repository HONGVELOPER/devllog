package devlog.hong.domain.repository;

import devlog.hong.domain.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {

    void deleteByImg(String fileName);
}
