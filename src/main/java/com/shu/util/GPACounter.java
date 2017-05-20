package com.shu.util;

/**
 * Created by Dell on 2017/5/18.
 */
public class GPACounter {
    /**
     * 计算平均成绩
     * @param finalGrade
     * @param usualGrade
     * @param rate
     * @return 绩点 例3.7
     *
     * 百分制成绩				成绩等级		绩点
    90～100			     		A			4.0
    85～89.9					A—			3.7
    82～84.9					B＋			3.3
    78～81.9					B			3.0
    75～77.9					B—			2.7
    72～74.9					C＋			2.3
    68～71.9					C			2.0
    66～67.9					C—			1.7
    64～65.9					D			1.3
    60～63.9					D—			1.0
    ＜60						F			  0
     */
    public static Double countGPA(Double finalGrade, Double usualGrade, double rate){
        double grade = finalGrade * rate + usualGrade * (1 - rate);
        return countGPA(grade);
    }

    public static Double countGPA(Double grade){
        if (grade >= 90 && grade <= 100) return 4.0;
        else if (grade >= 85 && grade < 90) return 3.7;
        else if (grade >= 82 && grade < 85) return 3.3;
        else if (grade >= 78 && grade < 82) return 3.0;
        else if (grade >= 75 && grade < 78) return 2.7;
        else if (grade >= 72 && grade < 75) return 2.3;
        else if (grade >= 68 && grade < 72) return 2.0;
        else if (grade >= 66 && grade < 68) return 1.7;
        else if (grade >= 64 && grade < 66) return 1.3;
        else if (grade >= 60 && grade < 64) return 1.0;
        else if (grade < 60) return 0.0;
        else return null;
    }

}
