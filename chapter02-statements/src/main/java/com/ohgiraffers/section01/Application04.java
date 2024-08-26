package com.ohgiraffers.section01;

import com.ohgiraffers.DTO.employeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application04 {
    public static void main(String[] args) {

        Connection con = getConnection();

        Statement stmt = null;
        ResultSet rset = null;

        employeeDTO selectemp = null;

        Scanner scr = new Scanner(System.in);
        System.out.println("조회하실 사번을 입력 해주세요 ");
        String empId = scr.nextLine();

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = " + empId;

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if(rset.next()){
                selectemp = new employeeDTO();

                selectemp.setEmpId(rset.getString("EMP_ID"));
                selectemp.setEmpName(rset.getString("EMP_NAME"));
                selectemp.setEmpNo(rset.getString("EMP_NO"));
                selectemp.setEmail(rset.getString("EMAIL"));
                selectemp.setPhone(rset.getString("PHONE"));
                selectemp.setDeptCode(rset.getString("DEPT_CODE"));
                selectemp.setJobCode(rset.getString("JOB_CODE"));
                selectemp.setSalLevel(rset.getString("SAL_LEVEL"));
                selectemp.setSalary(rset.getInt("SALARY"));
                selectemp.setBonus(rset.getDouble("BONUS"));
                selectemp.setManagerId(rset.getString("MANAGER_ID"));
                selectemp.setHireDate(rset.getDate("HIRE_DATE"));
                selectemp.setEntDate(rset.getDate("ENT_DATE"));
                selectemp.setEntYn(rset.getString("ENT_YN"));

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(con);
            close(stmt);
            close(rset);
        }
        System.out.println("selectEmp = " + selectemp);
    }
}
