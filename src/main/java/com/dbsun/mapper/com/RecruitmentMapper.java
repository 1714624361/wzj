package com.dbsun.mapper.com;

import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;

import java.util.List;

public interface RecruitmentMapper {

   int deleteRecruitmentByColumn(PageData pd);

   int addRecruitment(PageData pd);

   int addRecruitmentSelective(PageData pd);

   int addRecruitmentBatch(PageData pd);

   PageData getRecruitmentByColumn(PageData pd);

   int updateRecruitmentByid(PageData pd);

   int updateRecruitmentByColumn(PageData pd);

   //int updateRecruitmentBatch(PageData pd);

   List<PageData> getPageRecruitmentList(Page page);

   List<PageData> getRecruitmentList(PageData pd);


}
