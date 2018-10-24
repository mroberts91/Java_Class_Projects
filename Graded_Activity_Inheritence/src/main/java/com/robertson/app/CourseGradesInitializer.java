package com.robertson.app;

import com.robertson.domain.CourseGrades;
import com.robertson.domain.activites.Essay;
import com.robertson.domain.activites.FinalExam;
import com.robertson.domain.activites.GradedActivity;
import com.robertson.domain.activites.PassFailExam;
import com.robertson.domain.factories.ActivityFactory;
import com.robertson.domain.factories.ReportFactory;
import com.robertson.domain.interfaces.IReportable;

import java.util.List;

import static com.robertson.domain.enums.Activity.*;

public class CourseGradesInitializer {

    private static List<IReportable> reportableActivities;

    // Create a new course grades object and set all the graded activities.
    public static void initialiseCourseGrades(double labGrade, double passFailGrade, double essayGrade, double finalGrade){
        reportableActivities = null;
        GradedActivity labActivity = labActivityConfig(labGrade);
        PassFailExam passFailExam = passFailExamConfig(passFailGrade);
        Essay essay = essayActivityConfig(essayGrade);
        FinalExam finalExam = finalExamActivityConfig(finalGrade);
        CourseGrades cg = new CourseGrades(4, ReportFactory.gradeReport());
        cg.setLab(labActivity);
        cg.setPassFailExam(passFailExam);
        cg.setEssay(essay);
        cg.setFinalExam(finalExam);
        reportableActivities = cg.getReportableActivities();
    }

    // Instantiate a new graded activity and set the lab score
    private static GradedActivity labActivityConfig(double labScore) {
        GradedActivity lab;
        lab = ActivityFactory.getActivity(BASE);
        if (lab != null)
            lab.setScore(labScore);
        return lab;
    }

    // Instantiate a new pass/fail exam activity and set the exam score
    private static PassFailExam passFailExamConfig(double pfScore) {
        PassFailExam pf;
        pf = (PassFailExam)ActivityFactory.getActivity(PASS_FAIL);
        if (pf != null)
            pf.setScore(pfScore);
        return pf;
    }

    // Instantiate a new essay activity and set the essay score
    private static Essay essayActivityConfig(double essayScore) {
        Essay essay;
        essay = (Essay)ActivityFactory.getActivity(ESSAY);
        if (essay != null)
            essay.setScore(essayScore);
        return essay;
    }

    // Instantiate a new final exam activity and set the exam score
    private static FinalExam finalExamActivityConfig(double finalScore) {
        FinalExam finalExam;
        finalExam = (FinalExam)ActivityFactory.getActivity(FINAL_EXAM);
        if (finalExam != null)
            finalExam.setScore(finalScore);
        return finalExam;
    }

    // Get the last made list of reportable objects
    public static List<IReportable> getReportableActivities() {
        return reportableActivities;
    }
}
