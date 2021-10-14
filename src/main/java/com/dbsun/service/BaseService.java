package com.dbsun.service;

import com.dbsun.mapper.SysUserMapper;
import com.dbsun.util.UuidUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    @Autowired
    private SysUserMapper sysuserMapper;

    /**
     * 获得uuid专门用户service使用
     * */
    public String getUUID(){
        return UuidUtil.get32UUID();
    }

    /**
	 * 得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		return UuidUtil.get32UUID();
	}

	/**
	 * 获取操作失败返回的json
	 * @return
	 */
	public JSONObject getFalJson(){
		JSONObject json = new JSONObject();
		json.put("msg", "500");
		json.put("result", "filed");
		return json;
	}


}
