package devlog.hong.service.response;

import devlog.hong.controller.result.BaseResult;
import devlog.hong.controller.result.ListResult;
import devlog.hong.controller.result.SingleResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }

    public BaseResult getSuccessResult() {
        BaseResult result = new BaseResult();
        setSuccessResult(result);
        return result;
    }

    public BaseResult getFailResult() {
        BaseResult result = new BaseResult();
        setFailResult(result);
        return result;
    }

    public void setSuccessResult(BaseResult result) {
        result.setSuccess(true);
        result.setCode(BaseResponse.SUCCESS.getCode());
        result.setMsg(BaseResponse.SUCCESS.getMsg());
    }

    public void setFailResult(BaseResult result) {
        result.setSuccess(false);
        result.setCode(BaseResponse.FAIL.getCode());
        result.setMsg(BaseResponse.FAIL.getMsg());
    }
}
