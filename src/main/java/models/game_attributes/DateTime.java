package models.game_attributes;

public class DateTime {
    private int hour;
    private int day;
    private Season season;
    private WeekDays weekDay;

    public DateTime() {
        this.hour = 9;
        this.day = 1;
        this.season = Season.SPRING;
        this.weekDay = WeekDays.SUNDAY;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public WeekDays getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDays weekDay) {
        this.weekDay = weekDay;
    }
}
