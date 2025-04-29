package com.example.todo.config;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.example.todo.model.TaskStatus;

public class TaskStatusTypeHandler extends BaseTypeHandler<TaskStatus> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, TaskStatus parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public TaskStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value == null ? null : TaskStatus.fromValue(value);
    }

    @Override
    public TaskStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value == null ? null : TaskStatus.fromValue(value);
    }

    @Override
    public TaskStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value == null ? null : TaskStatus.fromValue(value);
    }
} 