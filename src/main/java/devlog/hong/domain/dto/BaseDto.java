package devlog.hong.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseDto {
    private int id;
    private LocalDateTime createDateTime = LocalDateTime.now();
    private LocalDateTime updateDateTime = LocalDateTime.now();
}
