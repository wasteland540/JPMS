package jpms.util.reports;

import java.util.List;
import jpms.util.reports.helperbeans.ReportPerson;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.dynamicreports.examples.Templates;
import static net.sf.dynamicreports.examples.Templates.boldStyle;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;

/**
 *
 * @author m.elz
 */
public class DefaultMemberlistReport {

    //titles
    private static final String memberlistTitle = "Memberlist";
    private static final String contactTitle = "Contact data";

    //headers
    private static final String firstnameHeader = "Firstname";
    private static final String lastnameHeader = "Lastname";
    private static final String ageHeader = "Age";
    private static final String birthdayHeader = "Birthday";
    private static final String dateOfEntersHeader = "Date of Enters";
    private static final String streetHeader = "Street";
    private static final String zipcodeHeader = "Zipcode";
    private static final String cityHeader = "City";
    private static final String voiceHeader = "Voice";
    private static final String functionRoleHeader = "func. Role";
    private static final String honorHeader = "Honor";
    private static final String contactTypeHeader = "Type";
    private static final String contactValueHeader = "Value";

    //fieldNames
    private static final String firstnameField = "firstname";
    private static final String lastnameField = "lastname";
    private static final String ageField = "age";
    private static final String birthdayField = "birthday";
    private static final String dateOfEntersField = "dateOfEnters";
    private static final String streetField = "street";
    private static final String zipcodeField = "zipcode";
    private static final String cityField = "city";
    private static final String voiceField = "voice";
    private static final String functionField = "functionRole";
    private static final String honorField = "honor";
    private static final String contactTypeField = "type";
    private static final String contactValueField = "value";

    public void show(List<ReportPerson> memberlist) {
        SubreportBuilder subReport = cmp.subreport(createSubReport())
                .setDataSource(exp.subDatasourceBeanCollection("contacts"));

        try {
            TextColumnBuilder<String> groupColumn = col.column("Choir", "group", type.stringType()).setStyle(boldStyle);
            
            report().setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
                    .setTemplate(Templates.reportTemplate)
                    .title(Templates.createTitleComponent(memberlistTitle))
                    .columns(groupColumn,
                            col.column(firstnameHeader, firstnameField, type.stringType()),
                            col.column(lastnameHeader, lastnameField, type.stringType()),
                            col.column(ageHeader, ageField, type.stringType()),
                            col.column(birthdayHeader, birthdayField, type.stringType()),
                            col.column(dateOfEntersHeader, dateOfEntersField, type.stringType()),
                            col.column(streetHeader, streetField, type.stringType()),
                            col.column(zipcodeHeader, zipcodeField, type.stringType()),
                            col.column(cityHeader, cityField, type.stringType()),
                            col.column(voiceHeader, voiceField, type.stringType()),
                            col.column(functionRoleHeader, functionField, type.stringType()),
                            col.column(honorHeader, honorField, type.stringType())
                    )
                    .detailFooter(
                            cmp.horizontalList(cmp.line(), cmp.line(), cmp.horizontalGap(150), subReport, cmp.horizontalGap(150)),
                            cmp.line()
                    )
                    .pageFooter(Templates.footerComponent)
                    .groupBy(groupColumn)
                    .setDataSource(memberlist)
                    .show(false);

        } catch (DRException e) {
        }
    }

    private JasperReportBuilder createSubReport() {
        JasperReportBuilder report = report();

        report.setTemplate(Templates.reportTemplate)
                .title(cmp.text(contactTitle).setStyle(Templates.boldCenteredStyle))
                .columns(
                        col.column(contactTypeHeader, contactTypeField, type.stringType()),
                        col.column(contactValueHeader, contactValueField, type.stringType())
                );

        return report;
    }
}
