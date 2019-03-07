public class RGV {
    private int point;
    private int washTime;
    private int[] runTime;
    private int maxNum;

    private Package aPackage;

    RGV(int point,int washTime,int[] runTime){
        this.point = point;
        this.washTime = washTime;
        this.runTime = runTime;
    }

    public int getNum() {
        return maxNum;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }

    //小车移动
    public int moveTo(int point){
        if (point != this.point) {
            int time = runTime[Math.abs(point - this.point) - 1];
            this.point = point;
            return time;
        }else {
            return 0;
        }
    }

    //上料
    public int AddMaterial(CNC c,int time,boolean isOutput,boolean testBad){
        //生成新的物料
        Package p = new Package();
        p.setStartTime(time);
        p.setCncId(c.getId());
        this.aPackage = p;

        //判断此次加工是否会故障
        boolean isBad = 100*Math.random() < 1;
        if (testBad&&isBad){
            c.setMalfunction(new Malfunction(p.getId(),c.getId(),time+c.getUpWorkTime()+(int)(c.getWorkTime()*Math.random()),10*60 + (int)(10*60*Math.random())));
        }

        if (c.getaPackage() != null) {
            c.getaPackage().setEndTime(time);
            maxNum = c.getaPackage().getId();
            this.aPackage = c.setaPackage(p,isOutput);
        }else {
            c.setaPackage(p,isOutput);
            this.aPackage = null;
        }

        //清除故障物料
        if (testBad && isBad){
            c.setaPackage(null,false);
        }

        return c.getUpWorkTime();
    }

    //清洗物料
    public int wash(){
        if (this.aPackage != null) {
            return this.washTime;
        }else {
            return 0;
        }
    }
}
