package cn.liyu.client.fallback;

import cn.liyu.client.UserRemoteClient;
import cn.liyu.common.core.global.SysStubInfo;
import cn.liyu.common.core.model.ResponseData;
import cn.liyu.common.core.user.UserInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liyu
 * date 2021/5/7 15:22
 * description
 */
@Slf4j
@AllArgsConstructor
public class UserRemoteFallbackImpl implements UserRemoteClient {

    private final Throwable throwable;

    /**
     * 查询用户信息信息
     *
     * @param userId
     * @param loginName
     * @return UserInfo
     */
    @Override
    public ResponseData<UserInfo> oneUser(Long userId, String loginName) {
        log.error("feign 调用失败{}", throwable.getLocalizedMessage());
        ResponseData<UserInfo> fail = new ResponseData<>();
        fail.setCode(SysStubInfo.FAIL.getCode());
        fail.setMsg(throwable.getLocalizedMessage());
        return fail;
    }
}
