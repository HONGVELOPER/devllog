package devlog.hong.domain.repository;

import devlog.hong.domain.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {

    @Modifying
    @Query("update PostEntity p set p.viewCount = p.viewCount + 1 where p.id = :id")
    void updateViewCount(@Param("id") int id);
}
