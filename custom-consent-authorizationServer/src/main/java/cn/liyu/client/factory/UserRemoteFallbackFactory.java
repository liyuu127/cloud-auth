package cn.liyu.client.factory;

import cn.liyu.client.UserRemoteClient;
import cn.liyu.client.fallback.UserRemoteFallbackImpl;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author liyu
 * date 2021/5/7 15:19
 * description
 */
@Component
public class UserRemoteFallbackFactory implements FallbackFactory<UserRemoteClient> {

    @Override
    public UserRemoteClient create(Throwable throwable) {
        return new UserRemoteFallbackImpl(throwable);
    }
}
