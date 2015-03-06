package jpms.util;

/**
 *
 * @author m.elz
 */
public class RegExValidator {

    private static final String datePattern = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    //private static final String zipcodePattern = "/\\b\\d{5}\\b/g";
    private static final String zipcodePattern = "\\b((?:0[1-46-9]\\d{3})|(?:[1-357-9]\\d{4})|(?:[4][0-24-9]\\d{3})|(?:[6][013-9]\\d{3}))\\b";

    public static boolean isVaildDate(String dateString) {
        return dateString.matches(datePattern);
    }
    
    public static boolean isVaildZipcode(String zipcodeString){
        return zipcodeString.matches(zipcodePattern);
    }
}
