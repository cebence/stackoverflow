import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


/**
 * >> Java date format string for date “Wednesday, January 2, 2013 5:29:26 PM +02:00” <<
 * http://stackoverflow.com/questions/15914753/java-date-format-string-for-date-wednesday-january-2-2013-52926-pm-0200
 *
 * input: "Wednesday, January 2, 2013 5:29:26 PM +02:00"
 * asking-for: working timestamp format string like "EEEE, MMM d, y hh:mm:ss a Z"
 *
 * @author dejan.cebetarevic
 */
public class StackOverflow15914753 {
  private static final String ISO_FORMAT_WITH_TZ = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
  private static final String ISO_FORMAT_NO_TZ = "yyyy-MM-dd'T'HH:mm:ss.SSS";
  private static final String CUSTOM_FORMAT_WITH_TZ = "EEEE, MMMM d, y hh:mm:ss a XXX";
  private static final String DEFAULT_INPUT = "Wednesday, January 2, 2013 5:29:26 PM +02:00";

  public static void main(String[] args) throws Exception {
    // Assume default input.
    String input = DEFAULT_INPUT;

    // But if it was given use it.
    if (args.length == 1) {
      input = args[0];
    }
    System.out.println("input = " + input);

    System.out.print("\nParsing input into date ............");
    Date d = createCustomDateFormat().parse(input);
    System.out.println("   [ OK ]\n");

    System.out.println("    date @ local = " + d);
    System.out.println("ISO date @ local = " + isoFormat(d));
    System.out.println("        UTC date = " + utcFormat(d));
  }

  public static String utcFormat(Date date) throws Exception {
    SimpleDateFormat utc = new SimpleDateFormat(ISO_FORMAT_WITH_TZ, Locale.UK);
    utc.setCalendar(Calendar.getInstance(TimeZone.getTimeZone("UTC")));
    return utc.format(date);
  }

  public static String isoFormat(Date date) throws Exception {
    SimpleDateFormat iso = new SimpleDateFormat(ISO_FORMAT_WITH_TZ);
    return iso.format(date);
  }

  public static SimpleDateFormat createCustomDateFormat() throws Exception {
    // Important to be US!
    SimpleDateFormat format = new SimpleDateFormat(CUSTOM_FORMAT_WITH_TZ, Locale.US);

    // Important to be +2 hours offset!
    format.setCalendar(Calendar.getInstance(TimeZone.getTimeZone("GMT+2")));
    return format;
  }
}
