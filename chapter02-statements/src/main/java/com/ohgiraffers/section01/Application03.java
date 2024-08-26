package com.ohgiraffers.section01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application03 {
    public static void main(String[] args) {

        // 이름을 입력받아서 해당 사원 아이디와 이름 조회
        // 쿼리문도 변수로 따로 만들어서 넣어주세요.

        Scanner scr = new Scanner(System.in);
        Connection con = getConnection();

        Statement stmt = null;
        ResultSet rset = null;

        try {
            stmt = con.createStatement();
            System.out.println("조회하고자 하는 이름을 입력해주세요");
            String name = scr.nextLine();
            String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_NAME ='" + name + "'";

            rset = stmt.executeQuery(query);
            while(rset.next()){
                System.out.println(rset.getString("EMP_ID") + " " );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            close(con);
            close(stmt);
            close(rset);
        }


    }
}
