package com.example.todo.config;

import com.example.todo.model.TaskStatus;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * Type handler for converting between TaskStatus enum and database values.
 * This handler is used by MyBatis to handle the conversion of TaskStatus enum
 * values when reading from and writing to the database.
 */
public class TaskStatusTypeHandler extends BaseTypeHandler<TaskStatus> {

  /**
   * Sets the TaskStatus value in a PreparedStatement.
   *
   * @param ps PreparedStatement to set the value in
   * @param i Parameter index
   * @param parameter TaskStatus value to set
   * @param jdbcType JDBC type
   * @throws SQLException if a database access error occurs
   */
  @Override
  public void setNonNullParameter(
      PreparedStatement ps, int i, TaskStatus parameter, JdbcType jdbcType) throws SQLException {
    ps.setString(i, parameter.getValue());
  }

  /**
   * Gets the TaskStatus value from a ResultSet.
   *
   * @param rs ResultSet to get the value from
   * @param columnName Name of the column
   * @return TaskStatus value
   * @throws SQLException if a database access error occurs
   */
  @Override
  public TaskStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
    String value = rs.getString(columnName);
    return value == null ? null : TaskStatus.fromValue(value);
  }

  /**
   * Gets the TaskStatus value from a ResultSet.
   *
   * @param rs ResultSet to get the value from
   * @param columnIndex Index of the column
   * @return TaskStatus value
   * @throws SQLException if a database access error occurs
   */
  @Override
  public TaskStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    String value = rs.getString(columnIndex);
    return value == null ? null : TaskStatus.fromValue(value);
  }

  /**
   * Gets the TaskStatus value from a CallableStatement.
   *
   * @param cs CallableStatement to get the value from
   * @param columnIndex Index of the column
   * @return TaskStatus value
   * @throws SQLException if a database access error occurs
   */
  @Override
  public TaskStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    String value = cs.getString(columnIndex);
    return value == null ? null : TaskStatus.fromValue(value);
  }
}
