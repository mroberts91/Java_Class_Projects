package com.robertson.domain.reporters;

import com.robertson.domain.interfaces.IReport;
import com.robertson.domain.interfaces.IReportable;

/*
    Grade Report is used to create a report based of an array of IReportables, this is only used in console applications.
 */
@Deprecated
public class GradeReport implements IReport
{
    @Override
    public String buildGradeReport(IReportable[] reportables)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("+-----------------+----------+------------+\n")
                .append(headerRow())
                .append("+-----------------+----------+------------+\n");
        for (IReportable reportable : reportables)
        {
            sb.append(
                    row(
                            reportable.getName(),
                            reportable.getGrade().toString(),
                            reportable.getPercentage()
                    ));
        }
        sb.append("+-----------------+----------+------------+\n");
        return sb.toString();
    }

    private String headerRow()
    {
        return String.format("| %-15s | %-8s | %-10s |%n", "Activity", "Grade", "Percentage");
    }

    private String row(String name, String grade, double score)
    {
        return String.format("| %-15s | %-8s | %-10.2f |%n", name, grade, score);
    }
}
