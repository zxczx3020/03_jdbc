package com.ohgiraffers.section02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application03 {
    public static void main(String[] args) {

        // 성씨를 입력 받아 해당 성을 가진 사원 조회
        // SELECT EMP_ID EMP_MAIN FROM EMPLOYEE WHERE EMP_NAME LIKE CONCAT(?,'%');

        Scanner scr = new Scanner(System.in);

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try {
            pstmt = con.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_NAME LIKE CONCAT(?, '%')");

            System.out.println("성씨를 입력해주세요 : ");

            pstmt.setString(1, scr.nextLine());

            rset = pstmt.executeQuery();

            while(rset.next()){
                System.out.println(rset.getString(1) + " " + rset.getString(2));
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        close(con);
        close(pstmt);
        close(rset);


    }
}
