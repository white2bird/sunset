package com.sunset.response;

import lombok.Data;

@Data
public class CompareResponse {

    private BodyCompositionResponse.BodyCompositionItem weight;
    private BodyCompositionResponse.BodyCompositionItem bmr;
    private BodyCompositionResponse.BodyCompositionItem dailyCaloricNeeds;
    private BodyCompositionResponse.BodyCompositionItem bmi;
    private BodyCompositionResponse.BodyCompositionItem bodyFat;
    private BodyCompositionResponse.BodyCompositionItem visceralFat;
    private BodyCompositionResponse.BodyCompositionItem visceralFatPercent;
    private BodyCompositionResponse.BodyCompositionItem skeletalMuscle;
    private BodyCompositionResponse.BodyCompositionItem skeletalMusclePercent;
    private BodyCompositionResponse.BodyCompositionItem muscle;
    private BodyCompositionResponse.BodyCompositionItem musclePercent;
    private BodyCompositionResponse.BodyCompositionItem boneMass;
    private BodyCompositionResponse.BodyCompositionItem waterPercent;
    private BodyCompositionResponse.BodyCompositionItem water;
    private BodyCompositionResponse.BodyCompositionItem protein;
    private BodyCompositionResponse.BodyCompositionItem proteinPercent;
    private BodyCompositionResponse.BodyCompositionItem fatFree;
    private BodyCompositionResponse.BodyCompositionItem fatMass;
    private BodyCompositionResponse.BodyCompositionItem subcutaneousFat;
    private BodyCompositionResponse.BodyCompositionItem weightControl;
    private BodyCompositionResponse.BodyCompositionItem fatLevel;
    private BodyCompositionResponse.BodyCompositionItem standWeight;
    private BodyCompositionResponse.BodyCompositionItem idealWeight;
    private BodyCompositionResponse.BodyCompositionItem bodyAge;
    private BodyCompositionResponse.BodyCompositionItem fatDegree;
    private BodyCompositionResponse.BodyCompositionItem fatBurningHeartRate;

}
