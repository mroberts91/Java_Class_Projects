package com.robertson.domain.factories;

import com.robertson.domain.interfaces.IReport;
import com.robertson.domain.reporters.GradeReport;

/*
    Factory returns a IReport object to the caller
 */
public class ReportFactory
{
    public static IReport gradeReport(){
        return new GradeReport();
    }
}
