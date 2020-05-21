package com.yun.pattern.template.jdbc.dao;

import com.yun.pattern.template.jdbc.JDBCTemplate;
import com.yun.pattern.template.jdbc.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

public class MemberDao extends JDBCTemplate {

    public MemberDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<?> selectAll(){
        String sql = "selcet * from t_member";
        return super.executeQuery(sql, new RowMapper<Object>() {
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
