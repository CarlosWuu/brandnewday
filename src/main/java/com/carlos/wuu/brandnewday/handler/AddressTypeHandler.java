package com.carlos.wuu.brandnewday.handler;

import com.carlos.wuu.brandnewday.enumEntity.AddressTypeEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressTypeHandler extends BaseTypeHandler<AddressTypeEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, AddressTypeEnum addressTypeEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,addressTypeEnum.getCityName());
    }

    @Override
    public AddressTypeEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if(resultSet.wasNull()){
            return null;
        }else{
            return AddressTypeEnum.getAddressTypeEnum((String)resultSet.getObject(s));
        }
    }

    @Override
    public AddressTypeEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if(resultSet.wasNull()){
            return null;
        }else{
            return AddressTypeEnum.getAddressTypeEnum((String)resultSet.getObject(i));
        }
    }

    @Override
    public AddressTypeEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        if(callableStatement.wasNull()){
            return null;
        }else{
            return AddressTypeEnum.getAddressTypeEnum((String)callableStatement.getObject(i));
        }
    }
}
