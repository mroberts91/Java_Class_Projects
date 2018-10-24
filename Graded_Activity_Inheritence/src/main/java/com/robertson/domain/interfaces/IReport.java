package com.robertson.domain.interfaces;

/*
    Interface for objects that issue grade reports
 */
public interface IReport
{
    String buildGradeReport(IReportable[] reportables);
}
