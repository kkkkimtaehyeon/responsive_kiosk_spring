package com.example.responsive_kiosk.aging;

import org.springframework.stereotype.Service;

@Service
public class AgingService {

    private static String AGE_YOUNG = "1";
    private static String AGE_MIDDLE = "2";
    private static String AGE_OLD = "3";

    /** UI 옵션(3종류)를 반환*/
    public String getUIOption(String ageData) {
        int age = Integer.parseInt(ageData);
        verifyAge(age);
        if(age >= 0 && age <= 30) {
            return AGE_YOUNG;
        }
        if(age > 30 && age <= 60) {
            return AGE_MIDDLE;
        }
        return AGE_OLD;
    }
    
    /** 나이가 0보다 작을 때 예외처리*/
    public void verifyAge(int age) throws IllegalArgumentException {
        if(age <= 0) {
            throw new IllegalArgumentException("나이가 0보다 작습니다.");
        }
    }
}
