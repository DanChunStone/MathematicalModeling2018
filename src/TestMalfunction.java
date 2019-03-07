/**
 * 使用顺序选择的原则，引入随机产生故障的机制
 * created：2018-9-16
 */

public class TestMalfunction {
    public static int time;
    public static RGV rgv;
    public static CNC[] list;

    public static void main(String[] args){
        initSys();

        for (int i = 0;i < 8;i++){
            work(list[i],true,true);
        }
        while (time < 3600 * 8){
            CNC c = getAFreeCNC();
            if (c != null){
                work(c,true,true);
            }else {
                time += 1;
            }
        }
        Malfunction.showAllMalfunction();
    }

    //决策
    public static CNC getAFreeCNC(){
        for (int i = 0;i < 8;i++){
            if (list[i].isWork(time) == false && list[i].isBad(time) == false) {
                return list[i];
            }
        }

        return null;
    }

    //系统完成一次工作
    public static void work(CNC c,boolean isOutput,boolean testBad){
        time += rgv.moveTo(c.getPoint());
        time += rgv.AddMaterial(c,time,isOutput,testBad);
        time += rgv.wash();
    }

    //初始化系统
    public static void initSys(){
        Package.init();
        time = 0;
        int[] times = {18,32,46};
        rgv = new RGV(1,25,times);
        list = new CNC[8];
        list[0] = new CNC(1,1,545,true,27);
        list[1] = new CNC(2,1,545,false,32);
        list[2] = new CNC(3,2,545,true,27);
        list[3] = new CNC(4,2,545,false,32);
        list[4] = new CNC(5,3,545,true,27);
        list[5] = new CNC(6,3,545,false,32);
        list[6] = new CNC(7,4,545,true,27);
        list[7] = new CNC(8,4,545,false,32);
    }
}
