package com.robertson.domain.factories;

import com.robertson.domain.activites.*;
import com.robertson.domain.enums.Activity;
import com.robertson.domain.enums.Grader;


/*
    Factory to initialize and return an activity object that is specified by the caller with the activityType argument
 */
public class ActivityFactory
{
    public static GradedActivity getActivity(Activity activityType){
        switch (activityType){
            case BASE:
                return new GradedActivity(
                        GraderFactory.getGrader(Grader.LETTER)
                );
            case PASS_FAIL:
                return new PassFailExam(
                        GraderFactory.getGrader(Grader.PASS_FAIL)
                );
            case ESSAY:
                return new Essay(
                        GraderFactory.getGrader(Grader.LETTER)
                );
            case FINAL_EXAM:
                return new FinalExam(
                        GraderFactory.getGrader(Grader.LETTER)
                );
            default:
                return null;
        }
    }
}
