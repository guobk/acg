package ${package_feign};

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/****
 * @Author: https://github.com/guobk/
 *****/
@FeignClient(name="${serviceName}")
@RequestMapping("/${table}")
public interface ${Table}Feign {

    /***
     * 查询${Table}表数据
     * @return
     */
    @RequestMapping(path = "/get${Table}List" ,method = RequestMethod.POST)
    Map get${Table}List(@RequestParam Map paramMap);

    /***
     * 删除${Table}表数据
     * @return
     */
    @RequestMapping(path = "/delete${Table}List" ,method = RequestMethod.POST)
    Map delete${Table}List(@RequestParam Map paramMap);

    /***
     * 更新${Table}表数据
     * @return
     */
    @RequestMapping(path = "/update${Table}" ,method = RequestMethod.POST)
    Map update${Table}(@RequestParam Map paramMap);

    /***
     * 新增${Table}表数据
     * @return
     */
    @RequestMapping(path = "/insert${Table}" ,method = RequestMethod.POST)
    Map insert${Table}(@RequestParam Map paramMap);

}
