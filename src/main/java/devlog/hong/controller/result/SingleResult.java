package devlog.hong.controller.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResult<T> extends BaseResult {
    private T data;
}
