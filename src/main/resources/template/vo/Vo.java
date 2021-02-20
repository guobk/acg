package ${package_vo};
<#if swagger==true>

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>

import java.io.Serializable;
<#list typeSet as set>
import ${set};
</#list>

/****
 * @Author: https://github.com/guobk/
 * @Description:${Table}构建
 *****/
<#if swagger==true>
@ApiModel(description = "${Table}",value = "${Table}")
</#if>
public class ${Table} implements Serializable{
<#list models as model>

	<#if swagger==true>
	@ApiModelProperty(value = "${model.desc!""}",required = false)
	</#if>
	private ${model.simpleType} ${model.name};
</#list>
<#list models as model>

	public ${model.simpleType} get${model.upperName}() {
		return ${model.name};
	}

	public void set${model.upperName}(${model.simpleType} ${model.name}) {
		this.${model.name} = ${model.name};
	}
</#list>
}
