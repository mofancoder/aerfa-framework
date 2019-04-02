package com.zhulong.business.system.baseservice.api.user.impl;

import com.zhulong.business.system.baseservice.api.user.UserBaseApi;
import com.zhulong.business.system.baseservice.entity.user.UserEntity;
import com.zhulong.framework.common.jpa.BaseDao;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import com.zhulong.business.system.baseservice.repository.user.UserRepository;
import com.zhulong.business.system.baseservice.dto.user.UserDTO;
import com.zhulong.business.system.baseservice.dto.user.UserQueryDTO;
import com.zhulong.framework.localmessage.service.LocalMessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 基础服务层-用户管理接口的实现
 * Created by shanb on 2019-1-16.
 */
@RestController
@Transactional
public class UserBaseApiImpl extends BaseDao implements UserBaseApi {

    @Autowired
    private UserRepository userBaseDao;

    @Autowired
    private LocalMessageService localMessageService;

    @Override
    public void save(@RequestBody UserDTO dto) {
        UserEntity userEntity = POJOConvertUtil.convert(dto,UserEntity.class);
        userBaseDao.save(userEntity);
        //FIXME:保存本地消息.作为示例而已，可以删除
        localMessageService.saveMessage(UUID.randomUUID().toString(),"user-create",userEntity.getGuid());
    }

    @Override
    public Boolean update(@RequestBody UserDTO dto) {
        UserEntity userEntity = POJOConvertUtil.convert(dto,UserEntity.class);
        userEntity = userBaseDao.update(userEntity);
        return userEntity!=null ? true : false;
    }

    @Override
    public Boolean deleteByGuid(@RequestParam("guid") String guid) {
        boolean result = true;
        try {
            userBaseDao.deleteById(guid);
        }catch (EmptyResultDataAccessException e){
            result = false;
        }
        return result;
    }

    @Override
    public UserDTO getByGuid(@RequestParam("guid") String guid) {
        UserEntity userEntity = userBaseDao.findById(guid).get();
        UserDTO userDto = POJOConvertUtil.convert(userEntity,UserDTO.class);
        return userDto;
    }

    @Override
    public List<UserDTO> findByGuidList(@RequestBody String[] guidList) {
        List<UserEntity> userEntities = userBaseDao.findAllById(Arrays.asList(guidList));
        List<UserDTO> userDtoList = POJOConvertUtil.convertList(userEntities,UserDTO.class);
        return userDtoList;
    }

    @Override
    public List<UserDTO> findByCondition(@RequestBody UserQueryDTO userQueryBean) {
        Example<UserEntity> example = getQueryExample(userQueryBean);
        List<UserEntity> userEntityList = userBaseDao.findAll(example,Sort.by(new Sort.Order(Sort.Direction.ASC,"sortNum"),new Sort.Order(Sort.Direction.DESC,"createTime")));
        return POJOConvertUtil.convertList(userEntityList,UserDTO.class);
    }

    private Example<UserEntity> getQueryExample(@RequestBody UserQueryDTO userQueryBean) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userQueryBean,userEntity);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues().withMatcher("guid", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.EXACT))
                .withMatcher("deptGuid",ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.EXACT))
                .withMatcher("creatorGuid",ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.EXACT));
        return Example.of(userEntity,exampleMatcher);
    }

    @Override
    public Pagination<UserDTO> findPageByCondition(@RequestBody UserQueryDTO userQueryBean) {
        Example<UserEntity> example = getQueryExample(userQueryBean);
        //jpa分页从0开始
        Page<UserEntity> page = userBaseDao.findAll(example, PageRequest.of(userQueryBean.getPageOrderDTO().getPageNo()-1,userQueryBean.getPageOrderDTO().getPageSize(),Sort.by(Sort.Order.asc("sortNum"),Sort.Order.desc("createTime"), Sort.Order.asc("guid"))));
        Pagination<UserDTO> rtPage = new Pagination<>(userQueryBean.getPageOrderDTO().getPageNo(),userQueryBean.getPageOrderDTO().getPageSize(),Long.valueOf(page.getTotalElements()).intValue());
        List<UserDTO> userDtoList = POJOConvertUtil.convertList(page.getContent(),UserDTO.class);
        rtPage.setList(userDtoList);
        return rtPage;
    }

    @Override
    public UserDTO getByAccount(@RequestParam("account") String account) {
        UserEntity userEntity = userBaseDao.getByAccount(account);
        if(userEntity!=null) {
            return POJOConvertUtil.convert(userEntity,UserDTO.class);
        }
        return null;
    }
}