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
    public Map get${Table}List(@RequestParam Map paramMap<#if swagger==true>@ApiParam(name = "${Table}对象",value = "传入JSON数据",required = false)</#if>){
        return ${table}Service.get${Table}List(paramMap);
    }

}
