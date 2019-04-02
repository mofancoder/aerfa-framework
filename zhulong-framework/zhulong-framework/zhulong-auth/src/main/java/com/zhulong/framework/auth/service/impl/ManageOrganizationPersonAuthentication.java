package com.zhulong.framework.auth.service.impl;

import com.zhulong.framework.auth.common.AuthOrganizationInfo;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.credence.AccountAuthInfo;
import com.zhulong.framework.auth.credence.CertCredence;
import com.zhulong.framework.auth.credence.PasswordCredence;
import com.zhulong.framework.auth.dto.AccountInfoDTO;
import com.zhulong.framework.auth.dto.AccountInfoQueryDTO;
import com.zhulong.framework.auth.dto.OrganizationBaseInfoDTO;
import com.zhulong.framework.auth.dto.OrganizationPersonDTO;
import com.zhulong.framework.auth.feign.OrganizationBaseApiFeign;
import com.zhulong.framework.auth.service.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by shanb on 2019-1-17.
 * 针对类型为内部用户的认证授权
 */
@Service("manageOrganizationPersonAuthentication")
public class ManageOrganizationPersonAuthentication implements Authentication {

    @Value("${lock.expire.time:1800000}")
    private Long lockExpireTime;

    @Autowired
    private OrganizationBaseApiFeign organizationBaseApi;

    @Override
    public AccountAuthInfo authPasswordCredence(PasswordCredence passwordCredence) {
        AccountAuthInfo accountAuthInfo = new AccountAuthInfo();
        if(passwordCredence!=null){
            AccountInfoQueryDTO accountInfoQueryDTO = new AccountInfoQueryDTO();
            accountInfoQueryDTO.setAccountType((short)2);
            accountInfoQueryDTO.setAccount(passwordCredence.getAccountName());
            List<AccountInfoDTO> accountInfoDTOList = organizationBaseApi.findAccountList(accountInfoQueryDTO);
            if(accountInfoDTOList!=null && accountInfoDTOList.size()==1){
                //查询机构人员的信息
                AccountInfoDTO accountInfoDTO = accountInfoDTOList.get(0);
                OrganizationPersonDTO personDTO = organizationBaseApi.getOrgPersonByGuid(accountInfoDTO.getRelateGuid());
                if(personDTO!=null){
                    AuthUser authUser = new AuthUser();
                    authUser.setGuid(personDTO.getGuid());
                    authUser.setName(personDTO.getName());
                    authUser.setIdNum(personDTO.getIdNum());
                    accountAuthInfo.setAccountGuid(accountInfoDTO.getGuid());
                    accountAuthInfo.setNeedMatchInfo(accountInfoDTO.getPassword());

                    accountAuthInfo.setLoginFuilreCount(accountInfoDTO.getLoginFailureCount());
                    accountAuthInfo.setStatus(accountInfoDTO.getStatus());
                    accountAuthInfo.setLockedTime(accountInfoDTO.getLockedStartTime());
                    //查询机构信息
                    OrganizationBaseInfoDTO orgBaseInfoDTO = organizationBaseApi.getOrgBaseInfoByGuid(personDTO.getOrganizationGuid());
                    if(orgBaseInfoDTO!=null){
                        AuthOrganizationInfo authOrganizationInfo = new AuthOrganizationInfo();
                        authOrganizationInfo.setGuid(orgBaseInfoDTO.getGuid());
                        authOrganizationInfo.setName(orgBaseInfoDTO.getName());
                        authOrganizationInfo.setUnifiedCode(orgBaseInfoDTO.getUnifiedCode());
                        authUser.setOrganizationInfo(authOrganizationInfo);
                    }
                    accountAuthInfo.setAuthUser(authUser);
                }
            }
        }
        return accountAuthInfo;
    }

    @Override
    public AccountAuthInfo authCaCredence(CertCredence certCredence) {
        //TODO:CA登录
        return null;
    }

    @Override
    public void loginSuccess(AccountAuthInfo accountAuthInfo) {
        if(!StringUtils.isEmpty(accountAuthInfo.getAccountGuid())) {
            AccountInfoDTO accountInfoDTO = new AccountInfoDTO();
            accountInfoDTO.setGuid(accountAuthInfo.getAccountGuid());
            accountInfoDTO.setLoginFailureCount(0);
            accountInfoDTO.setStatus((short)1);
            organizationBaseApi.updateAccountInfo(accountInfoDTO);
        }
    }

    @Override
    public void loginFailure(AccountAuthInfo accountAuthInfo,boolean lockedAccount) {
        if(!StringUtils.isEmpty(accountAuthInfo.getAccountGuid())){
            AccountInfoDTO accountInfoDTO = new AccountInfoDTO();
            accountInfoDTO.setGuid(accountAuthInfo.getAccountGuid());
            if(lockedAccount){
                accountInfoDTO.setStatus((short)3);
                accountInfoDTO.setLockedStartTime(System.currentTimeMillis());
                accountInfoDTO.setLockedEndTime(System.currentTimeMillis()+lockExpireTime);
            }else{
                accountInfoDTO.setLoginFailureCount(accountAuthInfo.getLoginFuilreCount()+1);
            }
            organizationBaseApi.updateAccountInfo(accountInfoDTO);
        }
    }

    @Override
    public Set<String> getAuthrazUrls(AuthUser user) {
        Set<String> urls = new HashSet<>();
        urls.add("/**");
        return urls;
    }
}