package com.dbsun.mapper.com;

import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;

import java.util.List;

public interface EngineeringMaterialsMapper {

   int deleteEngineeringMaterialsByColumn(PageData pd);

   int addEngineeringMaterials(PageData pd);

   int addEngineeringMaterialsSelective(PageData pd);

   int addEngineeringMaterialsBatch(PageData pd);

   PageData getEngineeringMaterialsByColumn(PageData pd);

   int updateEngineeringMaterialsByid(PageData pd);

   int updateEngineeringMaterialsByColumn(PageData pd);

   //int updateEngineeringMaterialsBatch(PageData pd);

   List<PageData> getPageEngineeringMaterialsList(Page page);

   List<PageData> getEngineeringMaterialsList(PageData pd);


}
