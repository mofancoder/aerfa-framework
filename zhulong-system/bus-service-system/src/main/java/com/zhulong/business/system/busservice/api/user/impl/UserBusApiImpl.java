package com.zhulong.business.system.busservice.api.user.impl;

import com.zhulong.business.system.busservice.api.user.UserBusApi;
import com.zhulong.business.system.busservice.dto.user.UserAddDTO;
import com.zhulong.business.system.busservice.dto.user.UserCgStatusDTO;
import com.zhulong.business.system.busservice.dto.user.UserEditDTO;
import com.zhulong.business.system.busservice.dto.user.UserListDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.ZhulongContext;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.annotion.Module;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.exception.SystemException;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import com.zhulong.framework.lock.LockHold;
import com.zhulong.framework.lock.ZhulongLock;
import com.zhulong.business.system.baseservice.api.user.UserBaseApi;
import com.zhulong.business.system.baseservice.dto.user.UserDTO;
import com.zhulong.business.system.baseservice.dto.user.UserQueryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by shanb on 2019-1-16.
 */
@RestController
@Module("USER")
@Slf4j
public class UserBusApiImpl implements UserBusApi {

    @Autowired
    private UserBaseApi userBaseApi;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ZhulongLock zhulongLock;

    @Override
    public ResultDTO<Pagination<UserListDTO>> findPage(@RequestBody UserQueryDTO userQueryBean) {
        Pagination<UserDTO> userDtoPage = userBaseApi.findPageByCondition(userQueryBean);
        //进行转化
        List<UserListDTO> userListDtoList = POJOConvertUtil.convertList(userDtoPage.getList(),UserListDTO.class);
        Pagination<UserListDTO> resultPage = userDtoPage.of(userListDtoList);
        return ResultDTO.<Pagination<UserListDTO>>builder().success(true).result(resultPage).build();
    }

    @Override
    public ResultDTO<Void> save(@Valid @RequestBody UserAddDTO dto, @AuthUserParam AuthUser authUser) {
        //前置条件检查  如账号是否已存在
        addUserPreCheck(dto);
        //获取当前用户信息
//        AuthUser authUser = ZhulongContext.getUser();
        Optional<LockHold> lockHold = zhulongLock.getLock(dto.getAccount(),10*1000);
        if(lockHold.isPresent()) {
            //业务逻辑校验
            try {
                String accout = dto.getAccount();
                UserQueryDTO queryBean = new UserQueryDTO();
                queryBean.setAccount(accout);
                List<UserDTO> userDtoList = userBaseApi.findByCondition(queryBean);
                if (!userDtoList.isEmpty()) {
                    throw new SystemException("ACCOUNT.REPEAT");
                }
                UserDTO userDto = new UserDTO();
                BeanUtils.copyProperties(dto, userDto);
                //其它需要设置值的字段处理
                //id一定要放在业务层进行设置。
                userDto.setGuid(UUID.randomUUID().toString());
                //TODO:是否有处理
                userDto.setCreateInfo(authUser);

                String password = passwordEncoder.encode(getDefaultPassword());
                userDto.setPassword(password);
                //测试幂等性保证
                for (int i = 0; i < 2; i++) {
                    userBaseApi.save(userDto);
                }
                return ResultDTO.<Void>builder().success(true).build();
            }finally {
                zhulongLock.releasLock(lockHold.get());
            }
        }else{
            return ResultDTO.<Void>builder().success(false).build();
        }
    }

    private void addUserPreCheck(UserAddDTO dto) {
        //业务逻辑校验
        String accout = dto.getAccount();
        UserQueryDTO queryBean = new UserQueryDTO();
        queryBean.setAccount(accout);
        List<UserDTO> userDtoList = userBaseApi.findByCondition(queryBean);
        if(!userDtoList.isEmpty()){
            throw new SystemException("ACCOUNT.REPEAT");
        }
    }

    @Override
    public ResultDTO<Boolean> update(@Valid @RequestBody UserEditDTO dto) {
        AuthUser authUser = ZhulongContext.getUser();
        updateUserPreCheck(dto);
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(dto,userDto);

        //TODO:设置需要手动设置的字段
        userDto.setModifyInfo(authUser);

        Boolean isUpdated = userBaseApi.update(userDto);
        if(isUpdated) {
            return ResultDTO.<Boolean>builder().success(true).result(isUpdated).build();
        }else{
            return ResultDTO.<Boolean>builder().success(false).build();
        }
    }

    private void updateUserPreCheck(UserEditDTO dto) {
        //业务逻辑校验
    }

    @Override
    public ResultDTO<UserDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser authUser) {
        UserDTO userDto = userBaseApi.getByGuid(guid);
        //去除敏感字段
        userDto.setPassword(null);
        return ResultDTO.<UserDTO>builder().success(true).result(userDto).build();
    }

    @Override
    public ResultDTO<Boolean> updateStatus(@Valid @RequestBody UserCgStatusDTO dto) {
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(dto,userDto);
        Boolean isUpdated = userBaseApi.update(userDto);
        if(isUpdated) {
            return ResultDTO.<Boolean>builder().success(true).result(isUpdated).build();
        }else{
            return ResultDTO.<Boolean>builder().success(false).build();
        }
    }

    @Override
    public ResultDTO<Boolean> restPassword(@RequestParam(value = "guid") String guid) {
        String password = passwordEncoder.encode(getDefaultPassword());
        UserDTO userDto = new UserDTO();
        userDto.setGuid(guid);
        userDto.setPassword(password);
        Boolean isUpdated = userBaseApi.update(userDto);
        if(isUpdated) {
            return ResultDTO.<Boolean>builder().success(true).result(isUpdated).build();
        }else{
            return ResultDTO.<Boolean>builder().success(false).build();
        }
    }

    @Override
    public ResultDTO<Boolean> unlockUser(@RequestParam("guid") String guid) {
        UserDTO userDto = new UserDTO();
        userDto.setGuid(guid);
        userDto.setLocked(false);
        Boolean isUpdated = userBaseApi.update(userDto);
        if(isUpdated) {
            return ResultDTO.<Boolean>builder().success(true).result(isUpdated).build();
        }else{
            return ResultDTO.<Boolean>builder().success(false).build();
        }
    }

    private String getDefaultPassword(){
        return "ABCD@123";
    }
}