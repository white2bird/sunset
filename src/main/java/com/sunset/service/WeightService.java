package com.sunset.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunset.converter.BodyConverter;
import com.sunset.entity.BodyComposition;
import com.sunset.entity.User.UserInfoEntity;
import com.sunset.entity.weight.WeightEntity;
import com.sunset.mapper.SignMapper;
import com.sunset.mapper.WeightMapper;
import com.sunset.request.*;

import com.sunset.response.BodyCompositionResponse;
import com.sunset.response.CompareResponse;
import com.sunset.response.HealthDataResponse;
import com.sunset.utils.BodyCalculator;
import com.sunset.utils.BodyCompositionCalculator;
import com.sunset.utils.UserIdThreadLocal;
import com.sunset.weightUtil4j.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WeightService extends ServiceImpl<WeightMapper, WeightEntity> {

    @Resource
    private HealthDataService healthDataService;
    @Resource
    private SignMapper signMapper;
    @Resource
    private BodyCompositionService bodyCompositionService;


    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> saveWeight(WeightRequest weightRequest, boolean save){
        // 保存体重
        WeightEntity weightEntity = new WeightEntity();
        weightEntity.setWeight(weightRequest.getWeight());
        weightEntity.setUid(UserIdThreadLocal.getUserId());
        weightEntity.setBlueAddress(weightRequest.getBlueAddress());
        if(save){
            this.save(weightEntity);
        }
        // todo 计算进行保存
        UserInfoEntity userInfoEntity = signMapper.GetUserInfo(UserIdThreadLocal.getUserId());
        Double height = Double.valueOf(userInfoEntity.getHeight());
        Double weight = weightRequest.getWeight().doubleValue();
        LocalDate localDate = LocalDate.parse(userInfoEntity.getBirthday());

        // 转换为 Date（需要指定时区）
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        int age = calculateAge(date);
        int sex = userInfoEntity.getSex();
        String sexChar = Objects.equals(0, sex) ? "FEMALE" : "MALE";
        BodyCompositionCalculator.Gender gender = BodyCompositionCalculator.Gender.valueOf(sexChar);
        String waistline = userInfoEntity.getWaistline();
        double waist = Double.valueOf(waistline);

        BodyCalculator calculator = new BodyCalculator(sexChar, age, weight, height, waist);


        BodyComposition latestWeighInfo = getLatestBodyComposition();

        BodyComposition  bodyComposition = new BodyComposition();
        bodyComposition.setUserId(UserIdThreadLocal.getUserId());
        bodyComposition.setWeightTime(new Date());
        bodyComposition.setHeight(height);
        bodyComposition.setBirthDate(date);
        bodyComposition.setGender(sex);
        bodyComposition.setWaistCircumference(waist);
        bodyComposition.setWeight(weightRequest.getWeight().doubleValue());
        bodyComposition.setDailyCaloricNeeds(calculator.calculateDailyCalories("lightly active"));
        bodyComposition.setBmr(calculator.calculateActualBMR());
        bodyComposition.setBmi(calculator.calculateBMI());
        bodyComposition.setBodyFat(calculator.calculateBodyFatPercentage()*100);
        bodyComposition.setVisceralFat(calculator.calculateVisceralFat());
        bodyComposition.setMuscle(calculator.muscleMassPercent()* 100);
        bodyComposition.setSkeletalMuscle(calculator.skeletalMuscle());
        // todo 不要
        bodyComposition.setSkeletalMusclePercentage(calculator.skeletalMusclePercent() * 100);
        bodyComposition.setBoneMass(calculator.boneMass());
        bodyComposition.setBodyWaterPercentage(calculator.waterPercent() * 100);

        bodyComposition.setProteinMass(calculator.ProteinMass());
        bodyComposition.setFatFreeMass(calculator.fatFreeMass());
        bodyComposition.setMuscleMass(calculator.muscleMass());
        bodyComposition.setFatMass(calculator.calFatMass());
        bodyComposition.setSubcutaneousFat(calculator.subcutaneousFatPercent()*100);
        bodyComposition.setWeightControl(calculator.weighControl());
        bodyComposition.setFatControl(calculator.fatControl());
        bodyComposition.setMuscleControl(calculator.muscleControl());
        bodyComposition.setObesityDegree(calculator.getFatLevel());
        bodyComposition.setIdealWeight(calculator.calculateIdealWeight());
        bodyComposition.setMetabolicAge(calculator.metabolicAge());
        bodyComposition.setObesityDegreeIndicator(calculator.fatPercent()*100);
        bodyComposition.setFatBurningHeartRate(calculator.fatBurningHeartRate());
        if(save){
            bodyCompositionService.save(bodyComposition);
        }

        Integer i = simulateVisceralFatLevel(bodyComposition.getBmi(), bodyComposition.getWaistCircumference(), bodyComposition.getGender() == 1);
        Map<String, Object> result = new HashMap<>();
        result.put("weight", buildWeightResult(bodyComposition));
        result.put("dailyCaloricNeeds", dailyCaloricNeeds(bodyComposition));
        result.put("bmr", buildBmrResult(bodyComposition));
        result.put("bmi", buildBmiResult(bodyComposition));
        result.put("bodyFat", buildBodyFatResult(bodyComposition));
        result.put("visceralFat", buildVisceralFatResult(bodyComposition, i.doubleValue()));
        result.put("visceralFatPercent", buildVisceralFatPercentResult(bodyComposition, i.doubleValue()));
        result.put("skeletalMuscle" ,buildSkeletalMuscleResult(bodyComposition));
        result.put("skeletalMusclePercent", buildSkeletalMusclePercentResult(bodyComposition));
        result.put("muscle", buildMuscleResult(bodyComposition));
        result.put("musclePercent", buildMusclePercentResult(bodyComposition));
        result.put("boneMass", buildBoneMassResult(bodyComposition));
        result.put("waterPercent", buildBodyWaterPercentResult(bodyComposition));
        result.put("water", buildBodyWaterResult(bodyComposition));
        result.put("protein", buildProteinResult(bodyComposition));
        result.put("proteinPercent", buildProteinPercentResult(bodyComposition));
        result.put("fatFree", buildFatFreeMassResult(bodyComposition));
        result.put("fatMass", buildFatMassResult(bodyComposition));
        result.put("subcutaneousFat", buildSubcutaneousFatResult(bodyComposition));
        result.put("weightControl", buildWeightControl(bodyComposition));
        result.put("fatLevel", buildFatLevel(bodyComposition));
        result.put("standWeight", buildStandWeight(bodyComposition));
        result.put("idealWeight", buildIdealWeight(bodyComposition));
        result.put("bodyAge", buildBodyAge(bodyComposition, age));
        result.put("fatDegree", fatDegree(bodyComposition));
        result.put("fatBurningHeartRate", buildBurningHeartRate(bodyComposition));

        if(latestWeighInfo != null){
            result.put("currentWeightTime", bodyComposition.getWeightTime());
            result.put("lastWeightTime", latestWeighInfo.getWeightTime());
            result.put("weightChange", buildWeightChange(bodyComposition, latestWeighInfo));
            result.put("bmiChange", buildBmiChange(bodyComposition, latestWeighInfo));
            result.put("bodyFatChange", buildBodyFatChange(bodyComposition, latestWeighInfo));
        }else{
            result.put("currentWeightTime", bodyComposition.getWeightTime());
            result.put("lastWeightTime", null);
            result.put("weightChange", null);
            result.put("bmiChange", null);
            result.put("bodyFatChange", null);

        }


        return result;

    }

    private Object buildBodyFatChange(BodyComposition bodyComposition, BodyComposition latestWeighInfo) {
        double v = bodyComposition.getBodyFat() - latestWeighInfo.getBodyFat();
        BigDecimal bigDecimal = new BigDecimal(v).setScale(2, RoundingMode.HALF_UP);
        return bigDecimal;
    }

    private Object buildBmiChange(BodyComposition bodyComposition, BodyComposition latestWeighInfo) {
        double v = bodyComposition.getBmi() - latestWeighInfo.getBmi();
        BigDecimal bigDecimal = new BigDecimal(v).setScale(2, RoundingMode.HALF_UP);
        return bigDecimal;
    }

    private Object buildWeightChange(BodyComposition bodyComposition, BodyComposition latestWeighInfo) {
        double v = bodyComposition.getWeight() - latestWeighInfo.getWeight();
        BigDecimal bigDecimal = new BigDecimal(v).setScale(2, RoundingMode.HALF_UP);
        return bigDecimal;
    }

    private Map<String, Object> buildWeightResult(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getWeight());
        result.putAll(WeightCal.calLevel(bodyComposition.getWeight()));
        return result;

    }

    /**
     * 进食量
     * @param bodyComposition
     * @return
     */
    private Map<String, Object> dailyCaloricNeeds(BodyComposition bodyComposition) {
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getDailyCaloricNeeds());
        result.putAll(DailyCaloricNeedsCal.calLevel(bodyComposition.getDailyCaloricNeeds()));
        return result;
    }

    /**
     * bmi
     * @param bodyComposition
     * @return
     */
    private Map<String, Object> buildBmiResult(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getBmi());
        result.putAll(BMiCal.calLevel(bodyComposition.getBmi()));
        return result;
    }

    /**
     * bmr 基础代谢
     * @return
     */
    private Map<String, Object> buildBmrResult(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getBmr());
        result.putAll(BmrCal.calLevel(bodyComposition.getBmr()));
        return result;
    }

    /**
     * 体脂率
     * @param bodyComposition
     * @return
     */
    private Map<String, Object> buildBodyFatResult(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getBodyFat());
        result.putAll( BodyFatCal.calLevel(bodyComposition.getBodyFat(), bodyComposition.getGender()==1));
        return result;
    }

    /**
     * 内脏脂肪
     * @param bodyComposition
     * @return
     */
    private Map<String, Object> buildVisceralFatResult(BodyComposition bodyComposition, Double visceralLevel){
        Map<String, Object> result = new HashMap<>();
        // 内脏脂肪率不需要等级区分

        result.put("value", visceralLevel);
        result.putAll(VisceralFatLevelCal.calLevel(visceralLevel));
//        result.put("standDataList", VisceralFatLevelCal.returnStandList());
//        result.put("standDataNameList", VisceralFatLevelCal.returnStandListName());
//        result.put("levelInfo", VisceralFatLevelCal.calLevel(bodyComposition.getVisceralFat()));
        return result;
    }

    /**
     * 内脏脂肪率
     * @param bodyComposition
     * @return
     */
    private Map<String, Object> buildVisceralFatPercentResult(BodyComposition bodyComposition, Double visceralLevel){
        Map<String, Object> result = new HashMap<>();
        result.put("value", visceralLevel);
        result.putAll(VisceralFatLvCal.calLevel(visceralLevel));
        return result;
    }

    /**
     * 骨骼肌重量
     * @param bodyComposition
     * @return
     */
    private Map<String, Object> buildSkeletalMuscleResult(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getSkeletalMuscle());
        result.putAll(SkeletalMuscleCal.calLevel(bodyComposition.getSkeletalMuscle()));
        return result;
    }

    /**
     * 骨骼肌率
     * @param bodyComposition
     * @return
     */
    private Map<String, Object> buildSkeletalMusclePercentResult(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getSkeletalMusclePercentage());
        result.putAll(SkeletalMusclePercentageCal.calLevel(bodyComposition.getSkeletalMusclePercentage()));
        return result;
    }

    /**
     * 肌肉重量
     * @param bodyComposition
     * @return
     */
    private Map<String, Object> buildMuscleResult(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getMuscleMass());
        result.putAll(MuscleMassCal.calLevel(bodyComposition.getMuscleMass()));
        return result;
    }

    /**
     * 肌肉率
     * @param bodyComposition
     * @return
     */
    private Map<String, Object> buildMusclePercentResult(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getMuscle());
        result.putAll(MusclePercentCal.calLevel(bodyComposition.getMuscle()));
        return result;
    }

    /**
     * 骨量
     * @return
     */
    private Map<String, Object> buildBoneMassResult(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getBoneMass());
        result.putAll(BoneCal.calLevel(bodyComposition.getBoneMass(), bodyComposition.getGender()==1));
        return result;
    }

    /**
     * 水分率
     * @return
     */
    private Map<String, Object> buildBodyWaterPercentResult(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getBodyWaterPercentage());
        result.putAll(WaterPercentCal.calLevel(bodyComposition.getBodyWaterPercentage(), bodyComposition.getGender()==1));
        return result;
    }

    /**
     * 水含量
     * @return
     */
    private Map<String, Object> buildBodyWaterResult(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        double water = bodyComposition.getBodyWaterPercentage() * bodyComposition.getWeight() / 100;
        result.put("value", water);
        result.putAll(WaterCal.calLevel(bodyComposition.getBodyWaterPercentage(), water,bodyComposition.getGender()==1));
        return result;
    }

    /**
     * 蛋白质
     * @return
     */
    private Map<String, Object> buildProteinResult(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getProteinMass());
        result.putAll(ProteinMassCal.calLevel(bodyComposition.getProteinMass()));
        return result;
    }


    /**
     * 蛋白质率
     * @return
     */
    private Map<String, Object> buildProteinPercentResult(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getProteinMass()/bodyComposition.getWeight() * 100);
        result.putAll(ProteinMassCal.calLevel(bodyComposition.getProteinMass()/bodyComposition.getWeight() * 100));
        return result;
    }

    /**
     * 去脂重量
     * @return
     */
    private Map<String, Object> buildFatFreeMassResult(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getFatFreeMass());
        result.putAll(FatFreeCal.calLevel(bodyComposition.getFatFreeMass()));
        return result;
    }

    /**
     * 脂肪重量
     * @return
     */
    private Map<String, Object> buildFatMassResult(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getFatMass());
        result.putAll(FatMassCal.calLevel(bodyComposition.getFatMass()));
        return result;
    }

    /**
     * 皮下脂肪率
     * @return
     */
    private Map<String, Object> buildSubcutaneousFatResult(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getSubcutaneousFat());
        result.putAll(SubcutaneousFatCal.calLevel(bodyComposition.getSubcutaneousFat(), bodyComposition.getGender() == 1));
        return result;
    }

    /**
     * 体重控制量
     * @return
     */
    private Map<String, Object> buildWeightControl(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getWeightControl());
        result.putAll(WeightControlCal.calLevel(bodyComposition.getWeightControl()));
        return result;
    }


    /**
     * 肥胖等级
     * @return
     */
    private Map<String, Object> buildFatLevel(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getObesityDegree());
        result.putAll(FatLevelCal.calLevel(bodyComposition.getBmi()));
        return result;
    }

    private Map<String, Object> buildStandWeight(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        double height = bodyComposition.getHeight();
        double standWeight = 22 * (height * height / 10000);
        result.put("value", standWeight);
        result.putAll(StandWeightCal.calLevel(standWeight, bodyComposition.getWeight()));
        return result;
    }

    private Map<String, Object> buildIdealWeight(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        result.putAll(IdeaWeightCal.calLevel(bodyComposition.getIdealWeight(), bodyComposition.getWeight()));
        return result;
    }

    private Map<String, Object> buildBodyAge(BodyComposition bodyComposition, Integer age){
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getMetabolicAge());
        result.putAll(BodyAgeCal.calLevel(bodyComposition.getMetabolicAge(), age));
        return result;
    }

    private Map<String, Object> fatDegree(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getMetabolicAge());
        result.putAll(FatDegreeCal.calLevel(bodyComposition.getWeight(), bodyComposition.getIdealWeight()));
        return result;
    }

    private Map<String, Object> buildBurningHeartRate(BodyComposition bodyComposition){
        Map<String, Object> result = new HashMap<>();
        result.put("value", bodyComposition.getFatBurningHeartRate());
        result.putAll(BurnFatHeartCal.calLevel(bodyComposition.getFatBurningHeartRate()));
        return result;
    }


    public Double getLatestWeigh(){
        LambdaQueryWrapper<BodyComposition> healthDataLambdaQueryWrapper = new LambdaQueryWrapper<>();
        healthDataLambdaQueryWrapper.orderByDesc(BodyComposition::getId);
        healthDataLambdaQueryWrapper.eq(BodyComposition::getUserId,UserIdThreadLocal.getUserId());
        healthDataLambdaQueryWrapper.last("limit 1");
        BodyComposition one = bodyCompositionService.getOne(healthDataLambdaQueryWrapper);
        return Objects.isNull(one) ? 0D : one.getWeight();
    }

    public List<BodyComposition> weightHistory(WeightHistoryPageQuery weightHistoryPageQuery){
        Page<BodyComposition> healthDataPage = new Page<>(weightHistoryPageQuery.getPageNo(), weightHistoryPageQuery.getPageSize());
        LambdaQueryWrapper<BodyComposition> healthDataLambdaQueryWrapper = new LambdaQueryWrapper<>();
        healthDataLambdaQueryWrapper.eq(BodyComposition::getUserId, UserIdThreadLocal.getUserId());
        healthDataLambdaQueryWrapper.ge(Objects.nonNull(weightHistoryPageQuery.getStart()), BodyComposition::getWeightTime, weightHistoryPageQuery.getStart());
        healthDataLambdaQueryWrapper.le(Objects.nonNull(weightHistoryPageQuery.getEnd()), BodyComposition::getWeightTime, weightHistoryPageQuery.getEnd());
        Page<BodyComposition> page = bodyCompositionService.page(healthDataPage, healthDataLambdaQueryWrapper);
        return page.getRecords();
    }


    public Map<String, Object> weightHistoryDetail(Long id){
        BodyComposition bodyComposition = bodyCompositionService.getById(id);
        WeightRequest weightRequest = new WeightRequest(BigDecimal.valueOf(bodyComposition.getWeight()), "");
        Map<String, Object> stringObjectMap = saveWeight(weightRequest, false);
        return stringObjectMap;
    }

    public Map<String, Object> historyTrend(HistoryTrendRequest historyTrendRequest){
        //
        if(Objects.equals(4, historyTrendRequest.getTimeType()) && (Objects.isNull(historyTrendRequest.getStart()) || Objects.isNull(historyTrendRequest.getEnd()))){
            throw new RuntimeException("参数错误");
        }
        LambdaQueryWrapper<BodyComposition> healthDataLambdaQueryWrapper = new LambdaQueryWrapper<>();
        Map<String, Date> query = buildTimeQuery(historyTrendRequest, healthDataLambdaQueryWrapper);
        healthDataLambdaQueryWrapper.orderByAsc(BodyComposition::getWeightTime);// 时间升序
        healthDataLambdaQueryWrapper.eq(BodyComposition::getUserId, UserIdThreadLocal.getUserId());
        List<BodyComposition> list = bodyCompositionService.list(healthDataLambdaQueryWrapper);
        // 根据结果返回不同的
        // 结果去重
//        list.stream().map()
        List<HealthDataResponse> responseList = BodyConverter.INSTANCE.toResponseList(list);
        Map<String, HealthDataResponse> dataByDay = responseList.stream()
                .collect(Collectors.groupingBy(HealthDataResponse::getTime,
                        LinkedHashMap::new,
                        Collectors.reducing(
                        (first, second) -> second // 保留最后一个元素（第二个参数）
                ))).entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().orElse(null) // 将 Optional 转换为实际对象
                ));
        List<Change> weightChangeList = new ArrayList<>();
        List<Change> bmiChangeList = new ArrayList<>();
        List<Change> bodyFatChangeList = new ArrayList<>();
        Double max = 0D;
        Double min = Double.MAX_VALUE;

        Map<String, Object> result = new HashMap<>();
        result.put("dayCount", dataByDay.size());
        result.put("startDay", getMonthDay(query.get("start")));
        result.put("endDay", getMonthDay(query.get("end")));
        result.put("change", 0);
        List<String> days = dataByDay.keySet().stream().collect(Collectors.toList());
        if(dataByDay.size() > 1){
            HealthDataResponse first = dataByDay.get(days.get(0));
            HealthDataResponse end = dataByDay.get(days.get(days.size()-1));
            result.put("change", end.getWeight()-first.getWeight());
        }
        // @Schema(description = "查询类型： 1：体重 2：bmi 3：体质率")
        for(String key : days){
            HealthDataResponse healthDataResponse = dataByDay.get(key);
            weightChangeList.add(new Change(key, healthDataResponse.getWeight()));
            bmiChangeList.add(new Change(key, healthDataResponse.getBmi()));
            bodyFatChangeList.add(new Change(key, healthDataResponse.getBodyFat()));

        }
        result.put("max", max);
        result.put("min", min);
        result.put("weightHistory", weightChangeList);
        result.put("bmiHistory", bmiChangeList);
        result.put("bodyFatHistory", bodyFatChangeList);
        return result;
    }

    public static String getMonthDay(Date date) {
        if (date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        return sdf.format(date);
    }

    /**
     * 体重比较
     * @return
     */
    public CompareResponse weightCompare(WeightCompareRequest weightCompareRequest){
        List<Long> ids = weightCompareRequest.getIds();
        ids = ids.stream().distinct().collect(Collectors.toList());
        if(!Objects.equals(2, ids.size())){
            throw new RuntimeException("必须需要两个元素");
        }
        LambdaQueryWrapper<BodyComposition> bodyCompositionLambdaQueryWrapper = new LambdaQueryWrapper<>();
        bodyCompositionLambdaQueryWrapper.in(BodyComposition::getId,ids);
        bodyCompositionLambdaQueryWrapper.orderByAsc(BodyComposition::getId);
        List<BodyComposition> list = bodyCompositionService.list(bodyCompositionLambdaQueryWrapper);
        if(list.size() != 2){
            throw new RuntimeException("必须需要两个元素");
        }
        BodyComposition oldData = list.get(0);
        BodyComposition newData = list.get(1);


        CompareResponse response = new CompareResponse();
        Map<String, Object> oldDataMap = saveWeight(new WeightRequest(new BigDecimal(oldData.getWeight()), ""), false);
        Map<String, Object> newDataMap = saveWeight(new WeightRequest(new BigDecimal(newData.getWeight()), ""), false);

        Field[] declaredFields = CompareResponse.class.getDeclaredFields();
        for(Field field : declaredFields){
            String name = field.getName();

            try{
                Double oldValue = getValue(name, oldDataMap);
                Double newValue = getValue(name, newDataMap);
                String oldColor = getColorValue(name, oldDataMap);
                String newColor = getColorValue(name, newDataMap);
                if(oldValue == null || newValue == null){
                    if(oldValue == null){
                        oldValue = 0D;
                    }
                    if(newValue == null){
                        newValue = 0D;
                    }
                }
                String oldStatus = getStatus(name, oldDataMap);
                String newStatus = getStatus(name, newDataMap);
                field.setAccessible(true);
                BodyCompositionResponse.BodyCompositionItem comparisonItem = createComparisonItem(newValue, oldValue, newStatus, oldStatus);
                comparisonItem.setCurrentColor(newColor);
                comparisonItem.setPreviousColor(oldColor);
                field.set(response, comparisonItem);
            }catch (Exception e){
                System.out.println("字段：" + name + "异常");
                e.printStackTrace();
            }

        }
        return response;

    }

    private String getColorValue(String name, Map<String, Object> dataMap) {
        Object o = dataMap.get(name);
        if(o == null){
            return null;
        }
        Map<String, Object> map = (Map<String, Object>) o;
        o = map.get("color");
        return String.valueOf(o);
    }

    private Double getValue(String name, Map<String, Object> dataMap){
        Object o = dataMap.get(name);
        if(o == null){
            return null;
        }
        Map<String, Object> map = (Map<String, Object>) o;
        o = map.get("value");
        Double value = null;
        if(o instanceof Integer){
            value = ((Integer) o).doubleValue();
        } else if(o instanceof Double){
            value = (Double) o;
        } else if(o instanceof BigDecimal){
            value = ((BigDecimal) o).doubleValue();
        }
        if(value == null){
            return null;
        }
        return value;
    }

    private String getStatus(String name, Map<String, Object> dataMap){

        return ((Map)dataMap.get(name)).get("levelName").toString();
    }

    private Map<String, Date> buildTimeQuery(HistoryTrendRequest historyTrendRequest, LambdaQueryWrapper<BodyComposition> healthDataLambdaQueryWrapper) {
        if(historyTrendRequest.getTimeType() != 4){
            return doStopTimeQuery(historyTrendRequest,  healthDataLambdaQueryWrapper);
        }
        healthDataLambdaQueryWrapper.ge(BodyComposition::getWeightTime, historyTrendRequest.getStart());
        healthDataLambdaQueryWrapper.le(BodyComposition::getWeightTime, historyTrendRequest.getEnd());
        Map<String, Date> result = new HashMap<>();
        result.put("start", historyTrendRequest.getStart());
        result.put("end", historyTrendRequest.getEnd());
        return result;
    }

    private Map<String, Date> doStopTimeQuery(HistoryTrendRequest historyTrendRequest, LambdaQueryWrapper<BodyComposition> healthDataLambdaQueryWrapper) {
        LocalDateTime now = LocalDateTime.now();
        switch (historyTrendRequest.getTimeType()){
            case 1:
                now = now.minusDays(7);
                break;
            case 2:
                now = now.minusDays(30);
                break;
            case 3:
                now = now.minusDays(90);
        }
        ZoneId zoneId = ZoneId.systemDefault();
        // 转换为 java.util.Date
        Date utilDate = Date.from(now.atZone(zoneId).toInstant());
        healthDataLambdaQueryWrapper.ge(BodyComposition::getWeightTime, utilDate);
        Map<String, Date> result = new HashMap<>();
        result.put("start", utilDate);
        result.put("end", new Date());
        return result;
    }

    public int calculateAge(Date birthday) {
        if (birthday == null) {
            return 0;
        }

        LocalDate birthDate = birthday.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate currentDate = LocalDate.now();

        return Period.between(birthDate, currentDate).getYears();
    }

    public Map<String, Object> getLatestWeighInfo() {
        LambdaQueryWrapper<BodyComposition> healthDataLambdaQueryWrapper = new LambdaQueryWrapper<>();
        healthDataLambdaQueryWrapper.orderByDesc(BodyComposition::getId);
        healthDataLambdaQueryWrapper.eq(BodyComposition::getUserId, UserIdThreadLocal.getUserId());
        healthDataLambdaQueryWrapper.last("limit 1");
        BodyComposition one = bodyCompositionService.getOne(healthDataLambdaQueryWrapper);
        if(one == null){
            return null;
        }
        Map<String, Object> result = saveWeight(new WeightRequest(new BigDecimal(one.getWeight()), ""), false);
        return result;

    }

    private BodyComposition getLatestBodyComposition() {
        LambdaQueryWrapper<BodyComposition> healthDataLambdaQueryWrapper = new LambdaQueryWrapper<>();
        healthDataLambdaQueryWrapper.orderByDesc(BodyComposition::getId);
        healthDataLambdaQueryWrapper.eq(BodyComposition::getUserId, UserIdThreadLocal.getUserId());
        healthDataLambdaQueryWrapper.last("limit 1");
        return bodyCompositionService.getOne(healthDataLambdaQueryWrapper);
    }

    public Boolean deleteHistory(DeleteHistoryWeightRequest deleteHistoryWeightRequest) {
        return bodyCompositionService.removeByIds(deleteHistoryWeightRequest.getIds());
    }




    private BodyCompositionResponse.BodyCompositionItem createComparisonItem(Double newValue, Double oldValue,
                                                                             String newStatus, String oldStatus) {
        BodyCompositionResponse.BodyCompositionItem item = new BodyCompositionResponse.BodyCompositionItem();
        item.setCurrentValue(newValue);
        item.setCurrentStatus(newStatus);
        item.setPreviousValue(oldValue);
        item.setPreviousStatus(oldStatus);
        double difference = newValue - oldValue;
        BigDecimal bigDecimal = new BigDecimal(difference).setScale(2, RoundingMode.HALF_UP);

        item.setChange(bigDecimal);

        return item;
    }

    // Status determination methods
    private String getObesityDegreeStatus(Double value) {
        if (value >= 15) return "偏胖";
        if (value >= 10) return "标准";
        return "偏低";
    }

    private String getBodyFatStatus(Double value) {
        if (value >= 25) return "超高";
        if (value >= 20) return "偏高";
        return "标准";
    }

    private String getFatMassStatus(Double value) {
        // Adjust thresholds based on your requirements
        if (value >= 18) return "超高";
        if (value >= 15) return "偏高";
        return "标准";
    }

    private String getSubcutaneousFatStatus(Double value) {
        if (value >= 16) return "超高";
        if (value >= 12) return "偏高";
        return "标准";
    }

    private String getFatFreeMassStatus(Double value) {
        // This might need adjustment based on height/weight
        return "标准"; // Typically no "status" for this metric
    }

    private String getMusclePercentageStatus(Double value) {
        if (value >= 70) return "优";
        if (value >= 60) return "良";
        return "不足";
    }

    private String getMuscleMassStatus(Double value) {
        // Adjust thresholds based on your requirements
        if (value >= 50) return "优";
        if (value >= 45) return "良";
        return "不足";
    }

    private String getSkeletalMuscleStatus(Double value) {
        if (value >= 34) return "优";
        if (value >= 30) return "良";
        return "不足";
    }

    private String getVisceralFatStatus(Double value) {
        if (value >= 9) return "偏高";
        if (value >= 6) return "标准";
        return "偏低";
    }

    private String getBodyWaterStatus(Double value) {
        if (value >= 55) return "偏高";
        if (value >= 50) return "标准";
        return "不足";
    }

    private String getBoneMassStatus(Double value) {
        // Adjust thresholds based on your requirements
        if (value >= 4.5) return "偏高";
        if (value >= 3.5) return "标准";
        return "偏低";
    }

    private String getProteinMassStatus(Double value) {
        if (value >= 16) return "标准";
        return "不足";
    }

    private String getMetabolicAgeStatus(Integer value) {
        // This should compare with chronological age
        // For simplicity, we'll use fixed thresholds
        if (value >= 50) return "偏大";
        if (value >= 40) return "标准";
        return "年轻";
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Change{
        private String day;
        private Double data;

    }

    public static int simulateVisceralFatLevel(double bmi, double waist, boolean male) {
        // 基础等级（BMI影响）
        int baseLevel = (int) (bmi * 0.8); // 假设BMI影响占比80%

        // 腰围影响（男性≥90cm，女性≥85cm加分）
        int waistBonus = 0;
        if ((male && waist >= 90) ||
                (!male && waist >= 85)) {
            waistBonus = (int) ((waist - (male ? 90 : 85)) / 2);
        }

        // 随机波动（模拟个体差异）
        int randomFactor = (int) (Math.random() * 5); // 0~4的随机数

        // 计算最终等级（限制在1-30）
        int level = baseLevel + waistBonus + randomFactor;
        return Math.max(1, Math.min(level, 30)); // 确保在1-30之间
    }
}
