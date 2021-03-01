package ${package_mapper};

import ${package_vo}.${Table};

import java.util.List;
import java.util.Map;

/****
 * @Author: guobk
 * @Description:${Table}的Dao
 *****/
public interface ${Table}Mapper {

  List<${Table}> get${Table}List(Map paramMap);

  int delete${Table}List(${Table} ${table});

  int update${Table}(${Table} ${table});

  int insert${Table}(${Table} ${table});
}
