package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application02 {
    public static void main(String[] args) {

        /*
            사용자가 원하는 메뉴를 등록할 수 있도록 만들어주세요.
            등록이 완료되면 성공, 실패하면 실패라고 출력해주세요.

         */
        int result = 0;
        Scanner scr = new Scanner(System.in);
        Connection con = getConnection();
        PreparedStatement pstmt = null;


        Properties prop = new Properties();

        System.out.println("추가할 음식 이름을 입력해주세요 : ");
        String scr1 = scr.nextLine();
        System.out.println("가격을 입력해주세요 : ");
        int scr2 = scr.nextInt();
        System.out.println("메뉴 카테고리를 입력해주세요 : ");
        int scr3 = scr.nextInt();
        System.out.println("판매 여부를 입력해주세요 : ");
        scr.nextLine();
        String scr4 = scr.nextLine();


        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));

            pstmt = con.prepareStatement(prop.getProperty("insertMenu"));

            pstmt.setString(1, scr1);
            pstmt.setInt(2, scr2);
            pstmt.setInt(3, scr3);
            pstmt.setString(4, scr4);

            result = pstmt.executeUpdate();

            if(result == 1){
                System.out.println("등록이 완료되었습니다.");

            }else{
                System.out.println("등록을 실패하였습니다.");
            }




        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
