package com.zhulong.business.system.baseservice.api.user;

import com.zhulong.framework.common.page.Pagination;
import com.zhulong.business.system.baseservice.dto.user.UserDTO;
import com.zhulong.business.system.baseservice.dto.user.UserQueryDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 提供基本的增删改查功能
 * Created by shanb on 2019-1-15.
 */
//方法上加上次注释，用feign调用，调用方的springmvc会将此类的接口开放出去。
//接口和实现类上都需要加上@RequestBody,@RequestParam注解。并且 @RequestParam注解，需要带上名称
//@RequestMapping("/userBase")
public interface UserBaseApi {

    /**
     *保存用户
     * @param dto 用户信息
     * @return 用户信息
     */
    @PostMapping("/userBase/save")
    void save(@RequestBody UserDTO dto);

    /**
     * 更新用户,更新非null值
     * @param dto 需要更新的值
     * @return 是否更新到数据
     */
    @PostMapping("/userBase/update")
    Boolean update(@RequestBody UserDTO dto);

    /**
     * 通过主键删除用户
     * @param guid 用户主键
     * @return 是否删除到数据
     */
    @PostMapping("/userBase/deleteByGuid")
    Boolean deleteByGuid(@RequestParam("guid") String guid);

    /**
     * 通过主键查询用户
     * @param guid 用户主键
     * @return 查询的用户信息
     */
    @GetMapping("/userBase/getByGuid")
    UserDTO getByGuid(@RequestParam("guid") String guid);

    /**
     *通过主键集合查询用户信息
     * @param guidList 用户主键列表
     * @return 查询到的用户列表
     */
    @PostMapping("/userBase/findByGuidList")
    List<UserDTO> findByGuidList(@RequestBody String[] guidList);

    /**
     *  通过条件查询用户列表
     *  @param userQueryBean 可支持的查询参数
     *  @return 用户列表信息
     */
    @PostMapping("/userBase/findByCondition")
    List<UserDTO> findByCondition(@RequestBody UserQueryDTO userQueryBean);

    /**
     *  通过条件查询用户列表
     *  @param userQueryBean 可支持的查询参数
     *  @return 用户分页数据
     */
    @PostMapping(value = "/userBase/findPageByCondition")
    Pagination<UserDTO> findPageByCondition(@RequestBody UserQueryDTO userQueryBean);

    /**
     * 通过账号获取用户信息
     * @param account 账号
     * @return 用户信息
     */
    @GetMapping("/userBase/getByAccount")
    UserDTO getByAccount(@RequestParam("account") String account);
}