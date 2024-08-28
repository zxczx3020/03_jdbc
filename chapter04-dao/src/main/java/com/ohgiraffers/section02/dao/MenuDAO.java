package com.ohgiraffers.section02.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class MenuDAO {

    private Properties prop = new Properties();

    public MenuDAO(String url){
        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int selectLastMenuCode(Connection con){

        Statement stmt = null;
        ResultSet rset = null;
        int maxCode = 0;

        String query = prop.getProperty("selectLastMenuCode");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if(rset.next()){
                maxCode = rset.getInt("MAX(MENU_CODE)");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(con);
            close(stmt);
            close(rset);

        }return maxCode;


    }
    public List<Map<Integer,String>> selectAllCategoryList(Connection con) {
        PreparedStatement pstmt1 = null;
        ResultSet rset1 = null;


        List<Map<Integer, String>> categoryList = null;

        try {

            String query = prop.getProperty("selectAllCategoryList");

            pstmt1 = con.prepareStatement(query);

            rset1 = pstmt1.executeQuery();

            categoryList = new ArrayList<>();

            while (rset1.next()) {
                Map<Integer, String> category = new HashMap<>();
                category.put(rset1.getInt("CATEGORY_CODE"), rset1.getString("CATEGORY_NAME"));
                categoryList.add(category);

            }

        }  catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt1);
            close(rset1);


        }return categoryList;


    }

}
