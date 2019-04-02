package com.zhulong.business.system.baseservice.api.commonbussetting;

import com.zhulong.business.system.baseservice.dto.commonbussetting.CodeRuleSettingDTO;
import com.zhulong.business.system.baseservice.dto.commonbussetting.CodeRuleSettingQueryDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.CodeRuleSettingEntity;
import com.zhulong.business.system.baseservice.repository.commonbussetting.CodeRuleSettingRepository;
import com.zhulong.framework.common.jpa.BaseDao;
import com.zhulong.framework.common.jpa.Finder;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * 描述：编码生成规则 BaseApiImpl实现
 *
 * @author 初。
 * @date 2019-03-18 11:44:45
 */
@Transactional
@RestController
public class CodeRuleSettingBaseApiImpl extends BaseDao implements CodeRuleSettingBaseApi {

    @Autowired
    private CodeRuleSettingRepository codeRuleSettingRepository;

    @Override
    public CodeRuleSettingDTO getByGuid(@RequestParam("guid") String guid) {

        Optional<CodeRuleSettingEntity> codeRuleSettingOptional = codeRuleSettingRepository.findById(guid);
        CodeRuleSettingDTO codeRuleSettingDTO = codeRuleSettingOptional.isPresent() ? POJOConvertUtil.convert(codeRuleSettingOptional.get(), CodeRuleSettingDTO.class) : null;

        return codeRuleSettingDTO;
    }

    @Override
    public List<CodeRuleSettingDTO> findByGuidList(@RequestBody List<String> guidList) {

        if (guidList != null) {
            List<CodeRuleSettingEntity> entityList = codeRuleSettingRepository.findAllById(guidList);
            return POJOConvertUtil.convertList(entityList, CodeRuleSettingDTO.class);
        }

        return null;
    }

    @Override
    public Pagination<CodeRuleSettingDTO> findPage(@RequestBody CodeRuleSettingQueryDTO queryDTO) {

        String hql = "select d from " + CodeRuleSettingEntity.class.getSimpleName() + " d where 1=1 ";

        Finder finder = Finder.create(hql);
        finder.append(" order by d.createTime desc ");

        Pagination pagination = this.find(finder, queryDTO.getPageOrderDTO().getPageNo(), queryDTO.getPageOrderDTO().getPageSize());
        pagination.setList(POJOConvertUtil.convertList(pagination.getList(), CodeRuleSettingEntity.class));

        return pagination;
    }

    @Override
    public void save(@RequestBody CodeRuleSettingDTO dto) {
        CodeRuleSettingEntity codeRuleSetting = POJOConvertUtil.convert(dto, CodeRuleSettingEntity.class);
        codeRuleSettingRepository.save(codeRuleSetting);
    }

    @Override
    public CodeRuleSettingDTO update(@RequestBody CodeRuleSettingDTO dto) {
        CodeRuleSettingEntity codeRuleSetting = POJOConvertUtil.convert(dto, CodeRuleSettingEntity.class);
        codeRuleSetting = codeRuleSettingRepository.update(codeRuleSetting);
        if (codeRuleSetting != null) {
            return POJOConvertUtil.convert(codeRuleSetting, CodeRuleSettingDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(@RequestParam("guid") String guid) {
        try {
            codeRuleSettingRepository.deleteById(guid);
        } catch (EmptyResultDataAccessException e) {
            //do nothing
        }
    }

}

