public class CNC {
    private int id;
    private int workTime;
    private int point;
    private boolean right;
    private int upWorkTime;
    private Malfunction malfunction;

    private Package aPackage;

    CNC(int id,int point,int workTime,boolean right,int upWorkTime){
        this.id = id;
        this.workTime = workTime;
        this.point = point;
        this.right = right;
        this.upWorkTime = upWorkTime;
    }

    public Package getaPackage() {
        return aPackage;
    }

    public int getWorkTime() {
        return workTime;
    }

    public int getPoint() {
        return point;
    }

    public boolean isRight() {
        return right;
    }

    public int getUpWorkTime() {
        return upWorkTime;
    }

    public void setMalfunction(Malfunction malfunction) {
        this.malfunction = malfunction;
    }

    public int getId() {
        return id;
    }

    //对cnc进行下料的同时进行上料
    public Package setaPackage(Package aPackage,boolean isOutput) {
        Package p = this.aPackage;
        if (this.aPackage != null){
            this.aPackage.Destroy(isOutput);
        }
        this.aPackage = aPackage;
        return p;
    }

    //判断机器是否处于工作状态
    public boolean isWork(int time){
        if (this.aPackage != null)
            return (time - (this.aPackage.getStartTime() + upWorkTime) >= workTime)?false:true;
        else
            return false;
    }

    //判断机器是否故障
    public boolean isBad(int time){
        if (this.malfunction != null){
            if (time >= malfunction.getEndTime()){
                this.malfunction = null;
                return false;
            }else if (time >= malfunction.getStartTime()){
                return true;
            }
        }
        return false;
    }
}
