package com.ohgiraffers.section02.dto;
// data access object (dao) - 데이터베이스와 상호작용을 할 클래스
public class MenuDTO {
    private String name;
    private int price;
    private int categoryCode;
    private String status;

    public MenuDTO() {
    }

    public MenuDTO(String menuName, int menePrice, int categoryCode, String orderableStatus) {
        this.name = menuName;
        this.price = menePrice;
        this.categoryCode = categoryCode;
        this.status = orderableStatus;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public String getStatus() {
        return status;
    }

    public MenuDTO menuName(String name){
        this.name = name;
        return this;
    }

    public MenuDTO price(int price){
        if(price<=0){
            System.out.println("음수가 입력됨...");
        }else{
            this.price = price;
        }
        return this;
    }
    public MenuDTO categoryCode(int code){
        this.categoryCode = code;
        return this;


    }
    public MenuDTO status(String status){
        if(status.equals("예") || status.equals("Y") || status.equals("y")){
            this.status = "Y";
        }else if (status.equals("아니오") || status.equals("N") || status.equals("n")){
            this.status = "N";
        }else{
            System.out.println("잘못 입력됨..");
        }
        return this;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "menuName='" + name + '\'' +
                ", menePrice=" + price +
                ", categoryCode=" + categoryCode +
                ", orderableStatus='" + status + '\'' +
                '}';
    }
}
