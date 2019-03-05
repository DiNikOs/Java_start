/**
 * Java2. Core. HW2+
 * Так как, не уточнялось какие значения должны возвращаться в консоль int или String, решено максимально просто
 * @author Dmitriy Ostrovskiy
 * @version 0.1 date Mar 06, 2019
 */

package hw2;

enum DayOfWeek {
    
    MONDAY("Понедельник","40"),
    TUESDAY("Вторник","32"),
    WEDNESDAY("Среда","24"),
    THURSDAY("Четверг","16"),
    FRIDAY("Пятница","8"),
    SATURDAY("Суббота","выходной"),
    SUNDAY("Воскресенье","выходной");
       
    private String rus;
    private String workTime;

    DayOfWeek(String rus, String workTime) {
        this.rus = rus;
        this.workTime = workTime;
    }

    public String getRus() {
        return rus;
    }

    public String getWorkTime() {
        return workTime;
    }
}

class DayOfWeekHW2 {
    public static void main(String[] args) {
        DayOfWeek day = DayOfWeek.WEDNESDAY;
        System.out.println(day.getRus() + " уже " + (day.ordinal()+1) + " рабочий день, а по английски это звучит " + DayOfWeek.WEDNESDAY  +"\n");
        
        for (DayOfWeek o: DayOfWeek.values()) {
            System.out.println("Осталось рабочих часов если считать " + o.getRus() + ": " + o.getWorkTime() );
        }

       // System.out.println(DayOfWeek.valueOf("APPLE").ordinal());

    }
}
