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