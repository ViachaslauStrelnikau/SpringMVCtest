package com.test;

import javax.sql.DataSource;

public class DatabaseServiceImpl implements DatabaseService{

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
}
