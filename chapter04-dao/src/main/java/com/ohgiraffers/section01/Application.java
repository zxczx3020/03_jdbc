package com.ohgiraffers.section01;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application {
    public static void main(String[] args) {
        Connection con = getConnection();
        Properties prop = new Properties();

        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;

        ResultSet rset1 = null;
        ResultSet rset2 = null;

        List<Map<Integer,String>> categoryList = null;


        int result = 0;
        int result1 = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            String query = prop.getProperty("selectLastMenuCode");
            String query2 = prop.getProperty("selectAllCategoryList");
            String query3 = prop.getProperty("insertMenu");

            pstmt1 = con.prepareStatement(query);
            pstmt2 = con.prepareStatement(query2);
            pstmt3 = con.prepareStatement(query3);

            rset1 = pstmt1.executeQuery();
            if(rset1.next()){
                result = rset1.getInt("MAX(MENU_CODE)");
            }
            System.out.println("최신 메뉴 코드 : " + result);

            rset2 = pstmt2.executeQuery();
            categoryList = new ArrayList<>();
            while(rset2.next()){
                Map<Integer,String> category = new HashMap<>();
                category.put(rset2.getInt("CATEGORY_CODE"),rset2.getString("CATEGORY_NAME"));
                categoryList.add(category);
            }
            System.out.println("categoryList = " + categoryList);



            pstmt3.setString(1, "김밥");
            pstmt3.setInt(2, 15000);
            pstmt3.setInt(3, 4);
            pstmt3.setString(4, "Y");

            result1 = pstmt3.executeUpdate();

            System.out.println(result1);



        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
            close(con);
            close(pstmt1);
            close(rset1);
            close(pstmt2);
            close(rset2);
        }
    }


}
