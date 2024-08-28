package com.ohgiraffers.section02.controller;

import com.ohgiraffers.section02.dao.MenuDAO;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class MenuController {
    private MenuDAO menuDAO = new MenuDAO("src/main/resources/mapper/menu-query.xml");

    public void findMaxCode(){
        int result = menuDAO.selectLastMenuCode(getConnection2());
        System.out.println("최신 메뉴 코드 : " + result);


    }


    public void findMenu(){
        List<Map<Integer,String>> result1 = menuDAO.selectAllCategoryList(getConnection2());
        System.out.println("모든 카테고리 목록 : " + result1);



    }



}
