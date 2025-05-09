package org.example;

import java.util.Arrays;

public class ArrrayMax {
    public int max(int arr[]) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        ArrrayMax arrMax = new ArrrayMax();
        int[] arr = {1, 2, 3, 4, 5};
        int sum = Arrays.stream(arr).sum();
        System.out.println("Tong cac phan tu trong mang la: " + sum);
        System.out.println("Phan tu lon nhat trong mang la: " + arrMax.max(arr));

        //Cach 2
//        int [] arr2 = {10, 20, 30, 40, 50};
//        int max2 = Arrays.stream(arr2).max().getAsInt();
//        System.out.println("Phan tu lon nhat trong mang 2 la: " + max2);
    }
}
