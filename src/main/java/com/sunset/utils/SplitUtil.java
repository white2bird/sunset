package com.sunset.utils;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitUtil {

    /**
     * 使用二分定位获取索引
     * @param list
     * @param value
     * @return
     */
    public static Integer getIndex(List<Double> list, Double value){
        int start = 0;
        int end = list.size()-1;
        while (start <= end){
            int mid = (start+end)/2;
            if (list.get(mid) == value){
                return mid;
            }else if (list.get(mid) > value){
                end = mid-1;
            }else {
                start = mid+1;
            }
        }
        return start;
    }




    public static Map<String, Object> levelInfo(List<Double> list, List<String> nameList, List<String> analList, List<String> sportList, List<String> eatList, List<String> colorList, String desc, Double value){
        if(list.isEmpty() || nameList.isEmpty()){
            throw new RuntimeException("list is empty");
        }
        Integer index = getIndex(list, value);
        if(index > list.size()){
            index = list.size()-1;
        }
        Map<String, Object> result = new HashMap<>();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP); // 四舍五入，保留两位
        double newValue = bd.doubleValue();
        result.put("value", newValue); // 数据
        result.put("standDataList",  list); // 区分列表
        result.put("standDataNameList", nameList); // 对应的级别
        result.put("level", index); // 级别
        result.put("levelName", nameList.get(index)); // 级别对应的名字
        result.put("analyze", analList.get(index)); // 分析
        result.put("sportAdvice", sportList.get(index)); // 运动建议
        result.put("eatAdvice", eatList.get(index)); // 吃的建议
        result.put("desc", desc); // 解释
        result.put("color", colorList.get(index));
        result.put("colorList", colorList);
        return result;
    }

    public static void main(String[] args) {
        Integer index = getIndex(Arrays.asList(9.0D, 14D), 25D);
        System.out.println(index);
    }
}
