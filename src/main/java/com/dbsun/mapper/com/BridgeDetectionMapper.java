package com.dbsun.mapper.com;

import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;

import java.util.List;

public interface BridgeDetectionMapper {

   int deleteBridgeDetectionByColumn(PageData pd);

   int addBridgeDetection(PageData pd);

   int addBridgeDetectionSelective(PageData pd);

   int addBridgeDetectionBatch(PageData pd);

   PageData getBridgeDetectionByColumn(PageData pd);

   int updateBridgeDetectionByid(PageData pd);

   int updateBridgeDetectionByColumn(PageData pd);

   //int updateBridgeDetectionBatch(PageData pd);

   List<PageData> getPageBridgeDetectionList(Page page);

   List<PageData> getBridgeDetectionList(PageData pd);


}
