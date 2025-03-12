
public class MyDate {
    private int day, month, year;
    // January is 0 .... December is 11

    private int[] maxDays = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public MyDate(int day, int month, int year) {
        this.day = day;
        this.month = month - 1;
        this.year = year;
    }

    public void incrementDay() {
        int newDay = day + 1;
        int maxDay = maxDays[month];
        if (newDay > maxDay) {
            incrementMonth();
            day = 1;
        } else if (month == 1 && newDay == 29 && !leapYear()) {
            day = 1;
            incrementMonth();
        } else {
            day = newDay;
        }
    }

    public void incrementDay(int diff) {
        while (diff > 0) {
            incrementDay();
            diff--;
        }
    }

    private boolean leapYear() {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    public void incrementYear(int diff) {
        year += diff;
        if (month == 1 && day == 29 && !leapYear()) {
            day = 28;
        }
    }

    public void incrementYear() {
        incrementYear(1);
    }

    public void decrementDay() {
        int newDay = day - 1;
        if (newDay == 0) {
            decrementMonth();
            day = maxDays[month];
        } else {
            day = newDay;
        }
    }

    public void decrementDay(int diff) {
        while (diff > 0) {
            decrementDay();
            diff--;
        }
    }

    public void decrementYear(int diff) {
        incrementYear(-diff);
    }

    public void decrementYear() {
        decrementYear(1);
    }

    public void decrementMonth(int diff) {
        int newMonth = (month - diff) % 12;
        int yearDiff = 0;
        if (newMonth < 0) {
            newMonth += 12;
            yearDiff = -1;
        }
        yearDiff += (month - diff) / 12;

        month = newMonth;
        year += yearDiff;
        if (day > maxDays[month]) {
            day = maxDays[month];
        }
        if (month == 1 && day == 29 && !leapYear()) {
            day = 28;
        }
    }

    public void decrementMonth() {
        decrementMonth(1);
    }

    public void incrementMonth(int diff) {
        int newMonth = (month + diff) % 12;
        int yearDiff = 0;
        if (newMonth < 0) {
            newMonth += 12;
            yearDiff = -1;
        }
        yearDiff += (month + diff) / 12;

        month = newMonth;
        year += yearDiff;
        if (day > maxDays[month]) {
            day = maxDays[month];
        }
        if (month == 1 && day == 29 && !leapYear()) {
            day = 28;
        }
    }

    public void incrementMonth() {
        incrementMonth(1);
    }

    public boolean isBefore(MyDate anotherDate) {
        if (this.year < anotherDate.year)
            return true;
        else if (this.year == anotherDate.year &&
                this.month < anotherDate.month)
            return true;
        else return this.year == anotherDate.year &&
                    this.month == anotherDate.month && this.day < anotherDate.day;
    }

    public boolean isAfter(MyDate anotherDate) {
        if (this.year > anotherDate.year)
            return true;
        else if (this.year == anotherDate.year &&
                this.month > anotherDate.month)
            return true;
        else return this.year == anotherDate.year &&
                    this.month == anotherDate.month && this.day > anotherDate.day;
    }

    public int dayDifference(MyDate anotherDate) {
        MyDate tempDate = new MyDate(this.day,
                this.month + 1, this.year);
        MyDate endDate = new MyDate(anotherDate.day,
                anotherDate.month + 1, anotherDate.year);

        int days = 0;
        while (!tempDate.toString().equals(endDate.toString())) {
            if (tempDate.isBefore(endDate)) {
                tempDate.incrementDay();
                days++;
            } else {
                tempDate.decrementDay();
                days--;
            }
        }
        return Math.abs(days);
    }


    public String toString() {
        return year + "-" + ((month + 1) < 10 ? "0" : "") + (month + 1)
                + "-" + (day < 10 ? "0" : "") + day;
    }
}
