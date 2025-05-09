package org.example;

public class namNhuan {
    public String checkNamNhuan(int nam) {
        if (nam % 4 == 0 && nam % 100 != 0 || nam % 400 == 0) {
            return "Nam nhuan";
        } else {
            return "Nam khong nhuan";
        }
    }

    public static void main(String[] args) {
        namNhuan namNhuan = new namNhuan();
        System.out.println(namNhuan.checkNamNhuan(2024)); // Nam nhuan
        System.out.println(namNhuan.checkNamNhuan(2023)); // Nam khong nhuan
    }
}
