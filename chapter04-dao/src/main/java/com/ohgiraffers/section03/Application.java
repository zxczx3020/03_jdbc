package com.ohgiraffers.section03;

import java.sql.Connection;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application {
    public static void main(String[] args) {

        // transaction 처리
        Connection con = getConnection2();

        try {
            con.setAutoCommit(false);

            System.out.println("autoCommit : " + con.getAutoCommit());

            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
