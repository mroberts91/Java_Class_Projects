package com.robertson.domain;

import com.robertson.domain.activites.Essay;
import com.robertson.domain.activites.FinalExam;
import com.robertson.domain.activites.GradedActivity;
import com.robertson.domain.activites.PassFailExam;
import com.robertson.domain.interfaces.IReport;
import com.robertson.domain.interfaces.IReportable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
    Course Grades class was required by the requirements documents for the project and its only responsibility is to
    hold the 4 graded activity objects
 */
public class CourseGrades
{
    private GradedActivity[] grades;
    private IReport reportMaker;

    public CourseGrades(int numberOfAssignments, IReport reportMaker){
        this.grades = new GradedActivity[numberOfAssignments];
        this.reportMaker = reportMaker;
    }

    // Setters to hold the graded activities in the grades array.
    public void setLab(GradedActivity labActivity){
        grades[0] = labActivity;
    }
    public void setPassFailExam(PassFailExam passFailExam){
        grades[1] = passFailExam;
    }
    public void setEssay(Essay essay){
        grades[2] = essay;
    }
    public void setFinalExam(FinalExam finalExam){
        grades[3] = finalExam;
    }

    @Override
    public String toString(){
        return reportMaker.buildGradeReport(grades);
    }

    // Getters
    public List<IReportable> getReportableActivities() {
        return new ArrayList<>(Arrays.asList(grades));
    }
}
