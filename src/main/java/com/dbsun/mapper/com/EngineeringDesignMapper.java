package com.dbsun.mapper.com;

import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;

import java.util.List;

public interface EngineeringDesignMapper {

   int deleteEngineeringDesignByColumn(PageData pd);

   int addEngineeringDesign(PageData pd);

   int addEngineeringDesignSelective(PageData pd);

   int addEngineeringDesignBatch(PageData pd);

   PageData getEngineeringDesignByColumn(PageData pd);

   int updateEngineeringDesignByid(PageData pd);

   int updateEngineeringDesignByColumn(PageData pd);

   //int updateEngineeringDesignBatch(PageData pd);

   List<PageData> getPageEngineeringDesignList(Page page);

   List<PageData> getEngineeringDesignList(PageData pd);


}
