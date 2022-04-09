package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.task.Task.FORMAT_DAY_OF_WEEK;
import static seedu.address.model.task.Task.FORMAT_MONTH;
import static seedu.address.model.task.Task.FORMAT_TIME;
import static seedu.address.model.task.Task.FORMAT_YEAR;
import static seedu.address.model.task.Task.getUserFriendlyDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import seedu.address.testutil.TaskBuilder;

public class TaskGetDateTimeStringTest {
    @Test
    public void getUserFriendlyDateTime_sameDay_today() {
        LocalDateTime today = LocalDateTime.now();

        String expected = String.format("Today, %s", today.format(FORMAT_TIME));

        assertEquals(expected, getUserFriendlyDateTime(today));
    }

    @Test
    public void getUserFriendlyDateTime_nextDay_tomorrow() {
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);

        String expected = String.format("Tomorrow, %s", tomorrow.format(FORMAT_TIME));

        assertEquals(expected, getUserFriendlyDateTime(tomorrow));
    }

    @Test
    public void getUserFriendlyDateTime_2daysFromNow_sameWeek() {
        LocalDateTime day = LocalDateTime.now().plusDays(2);

        String expected = day.format(FORMAT_DAY_OF_WEEK);

        assertEquals(expected, getUserFriendlyDateTime(day));
    }

    @Test
    public void getUserFriendlyDateTime_7daysFromNow_sameWeek() {
        LocalDateTime day = LocalDateTime.now().plusDays(7);

        String expected = day.format(FORMAT_DAY_OF_WEEK);

        assertEquals(expected, getUserFriendlyDateTime(day));
    }

    /**
     * To ensure that the day chosen is not within the next 7 days,
     * If today is first half of the year (Jan - Jun), use Dec 31.
     * Else use 1 Jan.
     */
    @Test
    public void getUserFriendlyDateTime_sameYear_monthDisplay() {
        LocalDateTime day;

        if (LocalDate.now().getMonthValue() <= 6) {
            day = LocalDateTime.now().withMonth(12).withDayOfMonth(31);
        } else {
            day = LocalDateTime.now().withMonth(1).withDayOfMonth(1);
        }

        String expected = day.format(FORMAT_MONTH);

        assertEquals(expected, getUserFriendlyDateTime(day));
    }

    @Test
    public void getUserFriendlyDateTime_nextYear_yearDisplay() {
        LocalDateTime day = LocalDateTime.now().plusYears(1);

        String expected = day.format(FORMAT_YEAR);

        assertEquals(expected, getUserFriendlyDateTime(day));
    }

    @Test
    public void getUserFriendlyDateTime_prevYear_yearDisplay() {
        LocalDateTime day = LocalDateTime.now().minusYears(1);

        String expected = day.format(FORMAT_YEAR);

        assertEquals(expected, getUserFriendlyDateTime(day));
    }

    @Test
    public void getDateTimeString_deadline_today() {
        LocalDateTime today = LocalDateTime.now();

        Task task = new TaskBuilder().withDateTime(today).withEndDateTime(null).build();

        String expected = String.format("Due: %s", getUserFriendlyDateTime(today));

        String actual = task.getDateTimeString();

        assertEquals(expected, actual);
    }

    @Test
    public void getDateTimeString_deadline_tomorrow() {
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);

        Task task = new TaskBuilder().withDateTime(tomorrow).withEndDateTime(null).build();

        String expected = String.format("Due: %s", getUserFriendlyDateTime(tomorrow));

        String actual = task.getDateTimeString();

        assertEquals(expected, actual);
    }

    @Test
    public void getDateTimeString_deadline_2daysFromNow() {
        LocalDateTime day = LocalDateTime.now().plusDays(2);

        Task task = new TaskBuilder().withDateTime(day).withEndDateTime(null).build();

        String expected = String.format("Due: %s", getUserFriendlyDateTime(day));

        String actual = task.getDateTimeString();

        assertEquals(expected, actual);
    }

    @Test
    public void getDateTimeString_deadline_7daysFromNow() {
        LocalDateTime day = LocalDateTime.now().plusDays(7);

        Task task = new TaskBuilder().withDateTime(day).withEndDateTime(null).build();

        String expected = String.format("Due: %s", getUserFriendlyDateTime(day));

        String actual = task.getDateTimeString();

        assertEquals(expected, actual);
    }

    @Test
    public void getDateTimeString_deadline_thisYear() {
        LocalDateTime day;

        if (LocalDate.now().getMonthValue() <= 6) {
            day = LocalDateTime.now().withMonth(12).withDayOfMonth(31);
        } else {
            day = LocalDateTime.now().withMonth(1).withDayOfMonth(1);
        }

        Task task = new TaskBuilder().withDateTime(day).withEndDateTime(null).build();

        String expected = String.format("Due: %s", getUserFriendlyDateTime(day));

        String actual = task.getDateTimeString();

        assertEquals(expected, actual);
    }

    @Test
    public void getDateTimeString_deadline_prevYear() {
        LocalDateTime day = LocalDateTime.now().minusYears(1);

        Task task = new TaskBuilder().withDateTime(day).withEndDateTime(null).build();

        String expected = String.format("Due: %s", getUserFriendlyDateTime(day));

        String actual = task.getDateTimeString();

        assertEquals(expected, actual);
    }

    @Test
    public void getDateTimeString_deadline_nextYear() {
        LocalDateTime day = LocalDateTime.now().plusYears(1);

        Task task = new TaskBuilder().withDateTime(day).withEndDateTime(null).build();

        String expected = String.format("Due: %s", getUserFriendlyDateTime(day));

        String actual = task.getDateTimeString();

        assertEquals(expected, actual);
    }

    @Test
    public void getDateTimeString_todayRange_noRepeated() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime start = today.withHour(0);
        LocalDateTime end = today.withHour(23);

        Task task = new TaskBuilder().withDateTime(start).withEndDateTime(end).build();

        String expected = String.format("From: %s - %s", getUserFriendlyDateTime(start), end.format(FORMAT_TIME));

        String actual = task.getDateTimeString();

        assertEquals(expected, actual);
    }

    @Test
    public void getDateTimeString_tomorrowRange_noRepeated() {
        LocalDateTime tomorrow = LocalDateTime.now();
        LocalDateTime start = tomorrow.withHour(0);
        LocalDateTime end = tomorrow.withHour(23);

        Task task = new TaskBuilder().withDateTime(start).withEndDateTime(end).build();

        String expected = String.format("From: %s - %s", getUserFriendlyDateTime(start), end.format(FORMAT_TIME));

        String actual = task.getDateTimeString();

        assertEquals(expected, actual);
    }

    @Test
    public void getDateTimeString_thisWeekRange_noRepeated() {
        LocalDateTime day = LocalDateTime.now().plusDays(3);
        LocalDateTime start = day.withHour(0);
        LocalDateTime end = day.withHour(23);

        Task task = new TaskBuilder().withDateTime(start).withEndDateTime(end).build();

        String expected = String.format("From: %s - %s", getUserFriendlyDateTime(start), end.format(FORMAT_TIME));

        String actual = task.getDateTimeString();

        assertEquals(expected, actual);
    }

    @Test
    public void getDateTimeString_thisYearRange_noRepeated() {
        LocalDateTime day;

        if (LocalDate.now().getMonthValue() <= 6) {
            day = LocalDateTime.now().withMonth(12).withDayOfMonth(31);
        } else {
            day = LocalDateTime.now().withMonth(1).withDayOfMonth(1);
        }

        LocalDateTime start = day.withHour(0);
        LocalDateTime end = day.withHour(23);

        Task task = new TaskBuilder().withDateTime(start).withEndDateTime(end).build();

        String expected = String.format("From: %s - %s", getUserFriendlyDateTime(start), end.format(FORMAT_TIME));

        String actual = task.getDateTimeString();

        assertEquals(expected, actual);
    }

    @Test
    public void getDateTimeString_anotherYearRange_noRepeated() {
        LocalDateTime day = LocalDateTime.now().withYear(1989);
        LocalDateTime start = day.withHour(0);
        LocalDateTime end = day.withHour(23);

        Task task = new TaskBuilder().withDateTime(start).withEndDateTime(end).build();

        String expected = String.format("From: %s - %s", getUserFriendlyDateTime(start), end.format(FORMAT_TIME));

        String actual = task.getDateTimeString();

        assertEquals(expected, actual);
    }

    @Test
    public void getDateTimeString_todayToTomorrow() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tomorrow = today.plusDays(1);

        Task task = new TaskBuilder().withDateTime(today).withEndDateTime(tomorrow).build();

        String expected = String.format("From: %s - %s",
                getUserFriendlyDateTime(today), getUserFriendlyDateTime(tomorrow));

        String actual = task.getDateTimeString();

        assertEquals(expected, actual);
    }

    @Test
    public void getDateTimeString_todayToThisWeek() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime end = today.plusDays(5);

        Task task = new TaskBuilder().withDateTime(today).withEndDateTime(end).build();

        String expected = String.format("From: %s - %s",
                getUserFriendlyDateTime(today), getUserFriendlyDateTime(end));

        String actual = task.getDateTimeString();

        assertEquals(expected, actual);
    }

    @Test
    public void getDateTimeString_tomorrowToThisYear() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tomorrow = today.plusDays(1);
        LocalDateTime day;

        if (LocalDate.now().getMonthValue() <= 6) {
            day = today.withMonth(12).withDayOfMonth(31);
        } else {
            day = today.withMonth(1).withDayOfMonth(1);
        }


        Task task = new TaskBuilder().withDateTime(tomorrow).withEndDateTime(day).build();

        String expected = String.format("From: %s - %s",
                getUserFriendlyDateTime(tomorrow), getUserFriendlyDateTime(day));

        String actual = task.getDateTimeString();

        assertEquals(expected, actual);
    }

    @Test
    public void getDateTimeString_thisYearToAnotherYear() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime day;
        LocalDateTime end = today.plusYears(3);

        if (LocalDate.now().getMonthValue() <= 6) {
            day = today.withMonth(12).withDayOfMonth(31);
        } else {
            day = today.withMonth(1).withDayOfMonth(1);
        }


        Task task = new TaskBuilder().withDateTime(day).withEndDateTime(end).build();

        String expected = String.format("From: %s - %s",
                getUserFriendlyDateTime(day), getUserFriendlyDateTime(end));

        String actual = task.getDateTimeString();

        assertEquals(expected, actual);
    }
}
