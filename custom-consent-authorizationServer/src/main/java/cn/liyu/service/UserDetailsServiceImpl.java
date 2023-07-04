package cn.liyu.service;

import cn.liyu.client.UserRemoteClient;
import cn.liyu.common.auth.model.SecurityUser;
import cn.liyu.common.core.user.User;
import cn.liyu.common.core.user.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liyu
 * date 2023/7/4 18:44
 * description:
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRemoteClient userRemoteClient;

    public UserDetailsServiceImpl(UserRemoteClient userRemoteClient) {
        this.userRemoteClient = userRemoteClient;
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = userRemoteClient.oneUser(null, username).getData();
        if (Objects.isNull(userInfo)) {
            log.info("username：" + username + " not found.");
            throw new UsernameNotFoundException("username:" + username + "not found");
        }


        return buildUserDetails(userInfo);
    }

    private static SecurityUser buildUserDetails(UserInfo userInfo) {
        User user = userInfo.getUser();
        List<String> roleScope = userInfo.getRoleScope();
        if (CollectionUtils.isEmpty(roleScope)) {
            roleScope = new ArrayList<>();
        }

        Set<String> permissionCode = roleScope.stream()
                .map(code -> "ROLE_" + code)
                .collect(Collectors.toSet());

        List<String> permissionScope = userInfo.getPermissionScope();
        if (!CollectionUtils.isEmpty(permissionScope)) {
            permissionCode.addAll(permissionScope);
        }

        Collection<? extends GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(permissionCode.toArray(new String[0]));
        return SecurityUser.builder()
                .userId(user.getId())
                .state(user.getState())
                .password(user.getPassword())
                .authorities(authorityList)
                .build();
    }
}
