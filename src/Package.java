public class Package {
    public static int number = 0;

    private int id;
    private int startTime;
    private int endTime;
    private int cncId;

    Package(){
        number += 1;
        this.id = number;
    }

    public static void init(){
        number = 0;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getId() {
        return id;
    }

    public void setCncId(int cncId) {
        this.cncId = cncId;
    }

    //物料下料时输出信息
    public void Destroy(boolean isOutput){
        String time1 = "" + startTime/3600 + ":" + (startTime%3600)/60 + ":" + ((startTime%3600)%60);
        String time2 = "" + endTime/3600 + ":" + (endTime%3600)/60 + ":" + ((endTime%3600)%60);

        if (isOutput)
            System.out.println("" + id + "," + cncId + "," + time1 + "," + time2);
    }
}
