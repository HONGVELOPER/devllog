package devlog.hong.controller.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResult {
    private boolean success;

    private int code;

    private String msg;
}
