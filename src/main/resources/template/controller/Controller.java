package ${package_controller};
<#if SearchType=="2" || SearchType=="3">import ${package_vo}.${Table};</#if>
import ${package_service}.${Table}Service;
<#if swagger==true>import io.swagger.annotations.*;</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/****
 * @Author: https://github.com/guobk/
 *****/
<#if swagger==true>@Api(value = "${Table}Controller")</#if>
@RestController
@RequestMapping("/${projectName}/${table}service")
public class ${Table}Controller {

    @Autowired
    private ${Table}Service ${table}Service;

    /***
     * 查询${Table}列表数据
     * @return
     */
    <#if swagger==true>
    @ApiOperation(value = "${Table}列表查询",notes = "${Table}列表查询详情",tags = {"${Table}Controller"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "paramMap", value = "需要查询的参数列表", required = true, dataType = "Map")
    })
    </#if>
    @RequestMapping(path = "/get${Table}List" ,method = RequestMethod.POST)
    public Map get${Table}List(@RequestParam Map paramMap<#if swagger==true>@ApiParam(name = "${Table}对象字段",value = "传入JSON数据",required = false)</#if>){
        return ${table}Service.get${Table}List(paramMap);
    }

    /***
     * 删除${Table}列表数据
     * @return
     */
    <#if swagger==true>
    @ApiOperation(value = "删除${Table}列表数据",notes = "删除${Table}列表数据",tags = {"${Table}Controller"})
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "paramMap", value = "删除数据依赖的参数列表", required = true, dataType = "Map")
    })
    </#if>
    @RequestMapping(path = "/delete${Table}List" ,method = RequestMethod.POST)
    public Map delete${Table}List(@RequestParam Map paramMap<#if swagger==true>@ApiParam(name = "${Table}对象字段",value = "传入JSON数据",required = false)</#if>){
        return ${table}Service.delete${Table}List(paramMap);
    }

    /***
     * 更新${Table}列表数据
     * @return
     */
    <#if swagger==true>
    @ApiOperation(value = "更新${Table}列表数据",notes = "更新${Table}列表数据",tags = {"${Table}Controller"})
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "paramMap", value = "更新数据依赖的参数列表", required = true, dataType = "Map")
    })
    </#if>
    @RequestMapping(path = "/update${Table}" ,method = RequestMethod.POST)
    public Map update${Table}(@RequestParam Map paramMap<#if swagger==true>@ApiParam(name = "${Table}对象字段",value = "传入JSON数据",required = false)</#if>){
        return ${table}Service.update${Table}(paramMap);
    }

    /***
     * 新增${Table}列表数据
     * @return
     */
    <#if swagger==true>
    @ApiOperation(value = "新增${Table}列表数据",notes = "新增${Table}列表数据",tags = {"${Table}Controller"})
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "paramMap", value = "新增数据依赖的参数列表", required = true, dataType = "Map")
    })
    </#if>
    @RequestMapping(path = "/insert${Table}" ,method = RequestMethod.POST)
    public Map insert${Table}(@RequestParam Map paramMap<#if swagger==true>@ApiParam(name = "${Table}对象字段",value = "传入JSON数据",required = false)</#if>){
        return ${table}Service.insert${Table}(paramMap);
    }

}
