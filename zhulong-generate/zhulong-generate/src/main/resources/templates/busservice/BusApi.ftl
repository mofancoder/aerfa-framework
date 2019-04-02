package ${package_name}.busservice.api${module_name};

import ${package_name}.baseservice.dto${module_name}.${table_name}DTO;
import ${package_name}.baseservice.dto${module_name}.${table_name}QueryDTO;
import ${package_name}.busservice.dto${module_name}.${table_name}ListDTO;
import ${package_name}.busservice.dto${module_name}.${table_name}SaveDTO;
import ${package_name}.busservice.dto${module_name}.${table_name}ShowDTO;
import ${package_name}.busservice.dto${module_name}.${table_name}UpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.page.Pagination;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述：${table_annotation} BusApi接口
 * @author ${author}
 * @date ${date}
 */
@RequestMapping("/${table_name?uncap_first}Bus")
@Api(value = "${table_annotation}-业务层接口",description = "${table_annotation}-业务层接口")
public interface ${table_name}BusApi {

    /**
     * 保存${table_annotation}
     * @param dto 保存的数据DTO
     * @param user 当前登录的用户
     * @return 保存后的对象信息
     */
    @PostMapping("save")
    @ApiOperation("保存${table_annotation}")
    ResultDTO<${table_name}DTO> save(@RequestBody ${table_name}SaveDTO dto, @AuthUserParam AuthUser user);

    /**
     * 更新${table_annotation}
     * @param dto 更新的数据DTO
     * @param user 当前登录用户
     * @return 更新后的数据对象
     */
    @PostMapping("update")
    @ApiOperation("更新${table_annotation}")
    ResultDTO<${table_name}DTO> update(@RequestBody ${table_name}UpdateDTO dto, @AuthUserParam AuthUser user);

    /**
     * 分页查询数据
     * @param queryDTO 查询参数对象
     * @param user 当前登录的用户
     * @return 获取到的分页信息
     */
    @PostMapping("findPage")
    @ApiOperation("查询分页数据")
    ResultDTO<Pagination<${table_name}ListDTO>> findPage(@RequestBody ${table_name}QueryDTO queryDTO,@AuthUserParam AuthUser user);

    /**
     *查询列表
     * @param queryDTO 查询的参数对象
     * @param user 当前登录的用户
     * @return 列表显示的数据
     */
    @PostMapping("findList")
    @ApiOperation("查询列表")
    ResultDTO<List<${table_name}ListDTO>> findList(@RequestBody ${table_name}QueryDTO queryDTO, @AuthUserParam AuthUser user);

    /**
     * 通过主键查询数据
     * @param guid 数据主键
     * @param user 当前登录的用户
     * @return 页面展示数据
     */
    @GetMapping("getByGuid")
    @ApiOperation("通过主键获取")
    ResultDTO<${table_name}ShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user);

    /**
     * 根据Id删除
     * @param guid 要删除数据的主键
     * @return 无-无论是否删除到数据，都表示成功。
     */
    @PostMapping("deleteById")
    @ApiOperation("通过主键删除数据")
    ResultDTO<Void> deleteById(@RequestParam("guid") String guid);

}
