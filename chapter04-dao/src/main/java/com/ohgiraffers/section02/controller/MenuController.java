package com.ohgiraffers.section02.controller;

import com.ohgiraffers.section02.dao.MenuDAO;
import com.ohgiraffers.section02.dto.MenuDTO;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

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

    public void insertMenu(){

        Scanner scr = new Scanner(System.in);

        MenuDTO menuDTO = new MenuDTO();
        System.out.println("메뉴 이름을 입력해주세요. ");
        menuDTO.menuName(scr.nextLine());
        System.out.println("메뉴 가격을 입력해주세요. ");
        menuDTO.price(scr.nextInt());
        System.out.println("카테고리 번호를 입력해주세요 : ");
        menuDTO.categoryCode(scr.nextInt());
        System.out.println("판매 여부를 등록 해 주세요. ");
        scr.nextLine();
        menuDTO.status(scr.nextLine());

        int result = menuDAO.insertMenu(getConnection2(), menuDTO);
        if(result >0){
            System.out.println("메뉴 등록 완료");
        }else{
            System.out.println("메뉴 등록 실패");
        }





    }



}
