package devlog.hong.controller.result;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResult<T> extends BaseResult {
    private List<T> list;
}
