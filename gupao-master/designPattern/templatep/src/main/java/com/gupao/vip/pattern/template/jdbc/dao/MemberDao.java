package com.gupao.vip.pattern.template.jdbc.dao;

import com.gupao.vip.pattern.template.jdbc.JDBCTemplate;
import com.gupao.vip.pattern.template.jdbc.RowMapper;
import com.gupao.vip.pattern.template.jdbc.entity.MemberEntity;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by qingbowu on 2019/3/18.
 */
public class MemberDao extends JDBCTemplate {


    public MemberDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<?> selectAll(){
        String sql = "selcet * from t_member";
        return super.executeQuery(sql, new RowMapper<MemberEntity>() {
            @Override
            public MemberEntity mapRow(ResultSet rs, int rowNum) throws Exception {
                MemberEntity entity = new MemberEntity();
                entity.setUsername(rs.getString("username"));
                entity.setPasspord(rs.getString("password"));
                entity.setNickname(rs.getString("nikename"));
                entity.setAge(rs.getInt("age"));
                entity.setAddr(rs.getString("addr"));
                return entity;
            }
        },null);
    }
}
