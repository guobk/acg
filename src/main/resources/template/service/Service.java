package ${package_service};

import java.util.Map;
/****
 * @Author: https://github.com/guobk/
 * @Description:${Table}业务层接口
 *****/
public interface ${Table}Service {

    /***
     * 查询${Table}列表数据
     * @param paramMap
     * @return
     */
    Map get${Table}List(Map paramMap);

    /***
     * 删除${Table}列表数据
     * @param paramMap
     * @return
     */
    Map delete${Table}List(Map paramMap);

    /***
     * 更新${Table}列表数据
     * @param paramMap
     * @return
     */
    Map update${Table}(Map paramMap);

    /***
     * 新增${Table}列表数据
     * @param paramMap
     * @return
     */
    Map insert${Table}(Map paramMap);
}
