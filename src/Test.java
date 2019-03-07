public class Test {
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
                work(c,true,false);
            }else {
                time += 1;
            }
        }
    }

    //决策
    public static CNC getAFreeCNC(){
        for (int i = 0;i < 8;i++){
            if (list[i].isWork(time) == false) {
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

        /**
         * 第一组：
         */
//        int[] times = {20,33,46};
//        rgv = new RGV(1,25,times);
//        list = new CNC[8];
//        list[0] = new CNC(1,1,560,true,28);
//        list[1] = new CNC(2,1,560,false,31);
//        list[2] = new CNC(3,2,560,true,28);
//        list[3] = new CNC(4,2,560,false,31);
//        list[4] = new CNC(5,3,560,true,28);
//        list[5] = new CNC(6,3,560,false,31);
//        list[6] = new CNC(7,4,560,true,28);
//        list[7] = new CNC(8,4,560,false,31);

        /**
         * 第二组：
         */
//        int[] times = {23,41,59};
//        rgv = new RGV(1,30,times);
//        list = new CNC[8];
//        list[0] = new CNC(1,1,580,true,30);
//        list[1] = new CNC(2,1,580,false,35);
//        list[2] = new CNC(3,2,580,true,30);
//        list[3] = new CNC(4,2,580,false,35);
//        list[4] = new CNC(5,3,580,true,30);
//        list[5] = new CNC(6,3,580,false,35);
//        list[6] = new CNC(7,4,580,true,30);
//        list[7] = new CNC(8,4,580,false,35);

        /**
         * 第三组：
         */
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
