package com.dbsun.mapper.com;

import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;

import java.util.List;

public interface TrafficFacilitiesMapper {

   int deleteTrafficFacilitiesByColumn(PageData pd);

   int addTrafficFacilities(PageData pd);

   int addTrafficFacilitiesSelective(PageData pd);

   int addTrafficFacilitiesBatch(PageData pd);

   PageData getTrafficFacilitiesByColumn(PageData pd);

   int updateTrafficFacilitiesByid(PageData pd);

   int updateTrafficFacilitiesByColumn(PageData pd);

   //int updateTrafficFacilitiesBatch(PageData pd);

   List<PageData> getPageTrafficFacilitiesList(Page page);

   List<PageData> getTrafficFacilitiesList(PageData pd);


}
