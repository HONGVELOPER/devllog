package devlog.hong.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class S3RequestDto {

    private List<String> deleteFileNameList;

    public void setDeleteFileNameList(List<String> deleteFileNameList) {
        this.deleteFileNameList = deleteFileNameList;
    }

}
