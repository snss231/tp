package seedu.address.commons.util;

import java.util.AbstractMap;
import java.util.Map;

public class TranslatorUtil {

    //@@author
    //Reused from https://www.baeldung.com/java-initialize-hashmap
    // with minor modifications
    private static final Map<String, Integer> periodMap = Map.ofEntries(
        new AbstractMap.SimpleEntry<String, Integer>("annually", 365),
        new AbstractMap.SimpleEntry<String, Integer>("quarterly", 120),
        new AbstractMap.SimpleEntry<String, Integer>("monthly", 30),
        new AbstractMap.SimpleEntry<String, Integer>("weekly", 7),
        new AbstractMap.SimpleEntry<String, Integer>("daily", 1)
    );
    //@@author

    /**
     * Get the mapping of period to no. of days.
     *
     * @return Return the mapping of period to no. of days.
     */
    public static Map<String, Integer> getPeriodMapping() {
        return periodMap;
    }
}
