package com.zhulong.business.system.busservice.api.user;

import com.zhulong.business.system.baseservice.dto.user.UserDTO;
import com.zhulong.business.system.baseservice.dto.user.UserQueryDTO;
import com.zhulong.business.system.busservice.dto.user.UserAddDTO;
import com.zhulong.business.system.busservice.dto.user.UserCgStatusDTO;
import com.zhulong.business.system.busservice.dto.user.UserEditDTO;
import com.zhulong.business.system.busservice.dto.user.UserListDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.page.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 业务层-用户管理接口
 * Created by shanb on 2019-1-16.
 */
@RequestMapping("/userBus")
@Api(description = "用户管理")
public interface UserBusApi {

    /**
     * 查询列表
     * @param userQueryBean 用户查询的bean
     * @return 用户列表分页信息
     */
    @PostMapping(value = "/findPage",consumes = "application/json")
    ResultDTO<Pagination<UserListDTO>> findPage(@RequestBody UserQueryDTO userQueryBean);

    /**
     * 增加用户
     * @param dto 传入所需增加的用户信息
     * @param authUser 认证的用户信息 由框架统一处理，无需传入。
     * @Return 返回结果，data为null
     */
    @PostMapping("/save")
    @ApiOperation(value="新增用户")
    ResultDTO<Void> save(@RequestBody UserAddDTO dto, @AuthUserParam AuthUser authUser);

    /**
     * 更新用户操作
     * @param dto 用户编辑信息
     * @return 返回是否更新到数据
     */
    @PostMapping("/update")
    ResultDTO<Boolean> update(@RequestBody UserEditDTO dto);

    /**
     * 根据guid获取用户信息
     * @param guid 要获取的用户信息
     * @param authUser 认证的用户信息
     * @return 返回查询到的用户
     */
    @GetMapping("/getByGuid")
    ResultDTO<UserDTO> getByGuid(String guid, @AuthUserParam AuthUser authUser);

    /**
     * 修改用户状态
     * @param dto 传入的要修改的信息
     * @return 返回是否修改成功。
     */
    @PostMapping("/updateStatus")
    ResultDTO<Boolean> updateStatus(@RequestBody UserCgStatusDTO dto);

    /**
     * 重置密码
     * @param guid 用户主键
     * @return 是否重置成功
     */
    @PostMapping("/restPassword")
    ResultDTO<Boolean> restPassword(String guid);

    /**
     * 解锁
     * @param guid 用户主键
     * @return 是否解锁成功
     */
    @PostMapping("/unlockUser")
    ResultDTO<Boolean> unlockUser(String guid);
}