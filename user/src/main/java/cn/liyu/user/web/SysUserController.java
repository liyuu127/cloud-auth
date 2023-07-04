package cn.liyu.user.web;

import cn.liyu.common.core.user.UserInfo;
import cn.liyu.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户接口
 *
 * @author liyu
 * date 2023/7/4 15:26
 * description:
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    private final UserService userService;

    public SysUserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 查询单个用户
     *
     * @param userId    用户id|1
     * @param loginName 登录名|liyu
     * @return User
     */
    @GetMapping("/")
    public UserInfo oneUser(@RequestParam(value = "userId", required = false) Long userId,
                            @RequestParam(value = "loginName", required = false) String loginName) {

        return userService.queryOneUser(userId, loginName);
    }
}
