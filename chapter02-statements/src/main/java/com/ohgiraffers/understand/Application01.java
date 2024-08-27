package com.ohgiraffers.understand;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application01 {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-understand.xml"));

            pstmt = con.prepareStatement(prop.getProperty("selectEmpByName1"));
            pstmt.setString(1, "200");

            rset = pstmt.executeQuery();

            while(rset.next()){
                System.out.println("사번 : "+rset.getString(1) + "   이름 :  " + rset.getString(2) + "   이메일 :  " + rset.getString(3) + "   핸드폰 :  " + rset.getString(4) + "   직급 :  " + rset.getString(5));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {


        close(con);
        close(pstmt);
        close(rset);
        }

    }
}
