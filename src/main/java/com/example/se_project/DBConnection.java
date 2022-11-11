package com.example.se_project;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBConnection {
    private final String SQL_CONNECTION = "jdbc:mysql://127.0.0.1:3306/project";
    private final String SQL = "jdbc:mysql://127.0.0.1:3306/project?"
            + "allowPublicKeyRetrieval=true&useSSL=false&user=root&password=OurPassword123";
    private Connection connection;
    private Statement statement;

    public DBConnection() throws SQLException {
        connection = DriverManager.getConnection(SQL);
        statement = connection.createStatement();
    }
    public void addEntryToDB(String query) throws SQLException {
        statement.execute(query);

    }
    public List<Map<String, Object>> getResults(String query) throws SQLException {
            ResultSet rs = statement.executeQuery(query);
            List<Map<String, Object>> results = resultSetToList(rs);
            return results;



    }
    public List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
        while(rs.next())
        {
            Map<String, Object> row = new HashMap<String, Object>(columns);
            for(int i=1;i<=columns;++i)
            {
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            rows.add(row);
        }
        return rows;
    }
    public void closeConnection() throws SQLException {
        connection.close();
    }

}
