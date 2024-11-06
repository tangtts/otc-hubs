package com.otc.hubs.TypeHandler;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 具体类型的TypeHandler
 */
@Slf4j
@MappedJdbcTypes(JdbcType.VARCHAR)  // 数据库类型
@MappedTypes({List.class})          // java数据类型
public class ListTypeHandler implements TypeHandler<List<String>> {

    @Override
    public void setParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        String hobbys = dealListToOneStr(parameter);
        ps.setString(i, hobbys);
    }

    /**
     * 集合拼接字符串
     *
     * @param parameter
     * @return
     */
    private String dealListToOneStr(List<String> parameter) {
        if (parameter == null || parameter.size() <= 0){
            return null;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < parameter.size(); i++) {
            if (i == parameter.size() - 1) {
                res.append(parameter.get(i));
                return res.toString();
            }
            res.append(parameter.get(i)).append(",");
        }
        return null;
    }

    @Override
    public List<String> getResult(ResultSet rs, String columnName) throws SQLException {
        if (StrUtil.isBlank(rs.getString(columnName))) {
            return new ArrayList<>();
        }
        return Arrays.asList(rs.getString(columnName).split(","));
    }

    @Override
    public List<String> getResult(ResultSet rs, int columnIndex) throws SQLException {
        if (StrUtil.isBlank(rs.getString(columnIndex))) {
            return new ArrayList<>();
        }
        return Arrays.asList(rs.getString(columnIndex).split(","));
    }


    @Override
    public List<String> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String hobbys = cs.getString(columnIndex);
        if (StrUtil.isBlank(hobbys)) {
            return new ArrayList<>();
        }
        return Arrays.asList(hobbys.split(","));
    }
}
