package com.ohgiraffers.section03.delete;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;


public class Application {
    public static void main(String[] args) {

        Scanner scr = new Scanner(System.in);
        Connection con = getConnection();      // connection 은 데이터베이스와 연결하는 객체
        PreparedStatement pstmt = null;        // 쿼리 저장 변수
        Properties prop = new Properties();
        int result = 0;


        System.out.println("삭제 할 메뉴 이름을 입력해주세요 : ");
        String name = scr.nextLine();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));

            pstmt = con.prepareStatement(prop.getProperty("deleteMenu"));  // 쿼리 선언

            pstmt.setString(1, name);
            result = pstmt.executeUpdate();

            if(result == 1){
                System.out.println(name + "메뉴가 삭제되었습니다.");

            }else{
                System.out.println("다시 시도 해주세요.");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        close(con);
        close(pstmt);

    }

}
