import java.util.ArrayList;
import java.util.List;

public class Malfunction {
    int packageId;
    int cncId;
    int startTime;
    int endTime;
    public static List<Malfunction> list = new ArrayList<>();

    Malfunction(int packageId,int cncId,int startTime,int stilTime){
        this.packageId = packageId;
        this.cncId = cncId;
        this.startTime = startTime;
        this.endTime = startTime + stilTime;

        list.add(this);
    }

    public void setCncId(int cncId) {
        this.cncId = cncId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void showMalfunction(){
        String time1 = "" + startTime/3600 + ":" + (startTime%3600)/60 + ":" + ((startTime%3600)%60);
        String time2 = "" + endTime/3600 + ":" + (endTime%3600)/60 + ":" + ((endTime%3600)%60);

        System.out.println("" + packageId + "," + cncId + "," + time1 + "," + time2);
    }

    public static void showAllMalfunction(){
        for (Malfunction m:list){
            m.showMalfunction();
        }
    }
}
