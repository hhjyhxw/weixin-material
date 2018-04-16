package com.icloud.dao.event;

import com.icloud.model.event.WxFans;
import java.math.BigDecimal;

public interface WxFansMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(WxFans record);

    int insertSelective(WxFans record);

    WxFans selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(WxFans record);

    int updateByPrimaryKey(WxFans record);
}