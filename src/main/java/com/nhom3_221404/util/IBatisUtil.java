package com.nhom3_221404.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public final class IBatisUtil {
    private static String configFile = "mybatis-config.xml";
    private static String environment = "development";
    private static String propertiesFile = "application.properties";
    private static SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

    public static final SqlSessionFactory buildSqlSessionFactory() {
        try {
            InputStream inputStream = Resources.getResourceAsStream(configFile);
            Properties properties = Resources.getResourceAsProperties(propertiesFile);
            return sqlSessionFactoryBuilder.build(inputStream, environment, properties);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final SqlSessionFactory buildSqlSessionFactoryTest() {
        try {
            InputStream inputStream = Resources.getResourceAsStream(configFile);
            Properties properties = Resources.getResourceAsProperties("test.properties");
            return sqlSessionFactoryBuilder.build(inputStream, environment, properties);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
