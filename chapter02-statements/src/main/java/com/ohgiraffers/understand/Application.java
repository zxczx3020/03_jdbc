package com.ohgiraffers.understand;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-understand.xml"));

            try {
                pstmt = con.prepareStatement(prop.getProperty("selectEmpByName"));
                rset = pstmt.executeQuery();

                while(rset.next()){

                    System.out.println(rset.getString(1)+ " " + rset.getString(2) + " " + rset.getString(3));

                }



            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        close(con);
        close(pstmt);
        close(rset);

    }
}
