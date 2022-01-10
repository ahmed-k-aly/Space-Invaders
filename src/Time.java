/* 
@author Ahmed Aly
@date 2020-11-16
Time class used for having a cooldown period for the bullets
*/
public class Time {

    private long start;
    private long end;


    public Time() {
    }

    public void startStopwatch() {
        start = System.currentTimeMillis();
    }

    public void stopStopwatch() {
        end = System.currentTimeMillis();
    }

    public long timePassed() {
        return end - start;
    }

    public long getEnd() {
        return end;
    }

    public long getStart() {
        return start;
    }
}