package ${package_name}.busservice.api${module_name}.impl;

import ${package_name}.baseservice.api${module_name}.${table_name}BaseApi;
import ${package_name}.baseservice.dto${module_name}.${table_name}DTO;
import ${package_name}.baseservice.dto${module_name}.${table_name}QueryDTO;
import ${package_name}.busservice.api${module_name}.${table_name}BusApi;
import ${package_name}.busservice.dto${module_name}.${table_name}ListDTO;
import ${package_name}.busservice.dto${module_name}.${table_name}SaveDTO;
import ${package_name}.busservice.dto${module_name}.${table_name}ShowDTO;
import ${package_name}.busservice.dto${module_name}.${table_name}UpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.annotion.Module;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

/**
 * 描述：${table_annotation} BusApiImpl实现
 * @author ${author}
 * @date ${date}
 */
@RestController
@Module("${moduleNameAnnotion}")
public class ${table_name}BusApiImpl implements ${table_name}BusApi {

    @Autowired
    private  ${table_name}BaseApi ${table_name?uncap_first}BaseApi;

    @Override
    public ResultDTO<${table_name}DTO> save(@Valid @RequestBody ${table_name}SaveDTO dto, @AuthUserParam AuthUser user) {

        ${table_name}DTO ${table_name?uncap_first}DTO = POJOConvertUtil.convert(dto,${table_name}DTO.class);
        ${table_name?uncap_first}DTO.setGuid(UUID.randomUUID().toString());
        ${table_name?uncap_first}DTO.setCreateInfo(user);
        ${table_name?uncap_first}BaseApi.save(${table_name?uncap_first}DTO);
        return ResultDTO.of(${table_name?uncap_first}DTO);
    }

    @Override
    public ResultDTO<${table_name}DTO> update(@Valid @RequestBody ${table_name}UpdateDTO dto, @AuthUserParam AuthUser user) {
        ${table_name}DTO ${table_name?uncap_first}DTO = POJOConvertUtil.convert(dto,${table_name}DTO.class);
        ${table_name?uncap_first}DTO.setModifyInfo(user);
        ${table_name?uncap_first}DTO = ${table_name?uncap_first}BaseApi.update(${table_name?uncap_first}DTO);
        return ResultDTO.of(${table_name?uncap_first}DTO);
    }

    @Override
    public ResultDTO<Pagination<${table_name}ListDTO>> findPage(@RequestBody ${table_name}QueryDTO queryDTO, @AuthUserParam AuthUser user) {
        Pagination<${table_name}DTO> ${table_name?uncap_first}DTOPage = ${table_name?uncap_first}BaseApi.findPage(queryDTO);

        List<${table_name}DTO> itemDTOList = ${table_name?uncap_first}DTOPage.getList();
        List<${table_name}ListDTO> listDTOList = get${table_name}ListDTOs(itemDTOList);
        return ResultDTO.of(${table_name?uncap_first}DTOPage.of(listDTOList));
    }

    private List<${table_name}ListDTO> get${table_name}ListDTOs(List<${table_name}DTO> itemDTOList) {

        return POJOConvertUtil.convertList(itemDTOList,${table_name}ListDTO.class);
    }

    @Override
    public ResultDTO<List<${table_name}ListDTO>> findList(@RequestBody ${table_name}QueryDTO queryDTO, @AuthUserParam AuthUser user) {
        List<${table_name}DTO> itemDTOList = ${table_name?uncap_first}BaseApi.findList(queryDTO);
        List<${table_name}ListDTO> listDTOList = get${table_name}ListDTOs(itemDTOList);
        return ResultDTO.of(listDTOList);
    }

    @Override
    public ResultDTO<${table_name}ShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user) {
        ${table_name}DTO ${table_name?uncap_first}DTO = ${table_name?uncap_first}BaseApi.getByGuid(guid);
        ${table_name}ShowDTO showDTO = POJOConvertUtil.convert(${table_name?uncap_first}DTO,${table_name}ShowDTO.class);
        return ResultDTO.of(showDTO);
    }

    @Override
    public ResultDTO<Void> deleteById(@RequestParam("guid") String guid) {
        ${table_name?uncap_first}BaseApi.deleteById(guid);
        return ResultDTO.of(null);
    }

}

