package cn.liyu.client;

import cn.liyu.client.factory.UserRemoteFallbackFactory;
import cn.liyu.common.core.model.ResponseData;
import cn.liyu.common.core.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liyu
 * date 2022/4/7 16:15
 * description
 */
@FeignClient(contextId = "userRemoteClient", value = "127.0.0.1:8003", path = "/user", fallbackFactory = UserRemoteFallbackFactory.class)
public interface UserRemoteClient {


    /**
     * 查询用户信息信息
     *
     * @return UserInfo
     */
    @GetMapping("/login/info")
    public ResponseData<UserInfo> oneUser(@RequestParam(value = "userId", required = false) Long userId,
                                          @RequestParam(value = "loginName", required = false) String loginName);
}