package jpms.util.reports.helperbeans;

/**
 *
 * @author m.elz
 */
public class ReportContact {
    
    private final String type;
    private final String value;

    public ReportContact(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
    
}
