package devlog.hong.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class PasswordRequestDto {
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @Builder
    public PasswordRequestDto(String password) {
        this.password = password;
    }
}
