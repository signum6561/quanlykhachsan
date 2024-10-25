package com.nhom3_221404.database.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import com.nhom3_221404.common.InvoiceType;

@MappedJdbcTypes(JdbcType.VARCHAR)
public class InvoiceTypeHandler extends BaseTypeHandler<InvoiceType> {

    @Override
    public InvoiceType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return InvoiceType.convert(rs.getString(columnName));
    }

    @Override
    public InvoiceType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return InvoiceType.convert(rs.getString(columnIndex));
    }

    @Override
    public InvoiceType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return InvoiceType.convert(cs.getString(columnIndex));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, InvoiceType parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, parameter.getCode());
    }

}
