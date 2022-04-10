package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypicalLocalDateTime {

    public static final LocalDateTime DATE_1 =
            LocalDateTime.of(2050,3,10,00,00);
    public static final LocalDateTime DATE_2 =
            LocalDateTime.of(2050,7,6,00,00);
    public static final LocalDateTime DATE_3 =
            LocalDateTime.of(2050,9,4,00,00);
    public static final LocalDateTime DATE_4 =
            LocalDateTime.of(2050,2,15,00,00);


    /**
     * Returns array of 2 test Local Date Time
     * @return Returns array of 2 test Local Date Time
     */
    public static List<LocalDateTime> getTypicalDateTimes() {
        return new ArrayList<>(Arrays.asList(DATE_2, DATE_3));
    }
}
