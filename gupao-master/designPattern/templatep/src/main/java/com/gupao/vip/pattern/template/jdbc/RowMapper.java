package com.gupao.vip.pattern.template.jdbc;

import java.sql.ResultSet;

/**
 * ORM映射定制化接口
 * Created by qingbowu on 2019/3/18.
 */
public interface RowMapper<T> {

    T mapRow(ResultSet rs,int rowNum) throws Exception;
}
