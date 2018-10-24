package com.robertson.domain.interfaces;

import com.robertson.domain.enums.Grade;

/*
    Interface for objects that are able to be reported on a grade report.
 */
public interface IReportable
{
    String getName();
    double getPercentage();
    Grade getGrade();
}
