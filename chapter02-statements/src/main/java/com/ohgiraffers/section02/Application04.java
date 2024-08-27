package com.ohgiraffers.section02;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application04 {
    public static void main(String[] args) {

        // xml 파일을 이용한 조회
        // xml - 특수한 목적을 가진 마크업 언어

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Scanner scr = new Scanner(System.in);

        System.out.println("성씨를 입력해주세요 : ");

        String empName = scr.nextLine();

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml"));

            try {
                pstmt = con.prepareStatement(prop.getProperty("selectEmpByName"));
                pstmt.setString(1, empName);
                rset = pstmt.executeQuery();

                while(rset.next()){
                    System.out.println(rset.getString(1) + " " + rset.getString(2));

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        close(con);
        close(rset);
        close(pstmt);

    }
}
