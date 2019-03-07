/**
 * 采用随机决策的方式模拟多次工作，获得较优解
 * 然后采用较优解的方案模拟工作，输出结果
 * created：2018-9-16
 */

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int time;
    public static RGV rgv;
    public static CNC[] list;

    public static List<Integer> myChoose;
    public static int chooseTime = 0;


    public static void main(String[] args){
        int max = 0;
        int N = 100000;
        for (int i = 0;i < N;i++) {
            int n = test();
            if (n>max)
                max = n;
        }

        System.out.println(max);

        for (int i = 0;i < N;i++) {
            int n = test();
            if (n == max){
                testAwork();
                break;
            }
        }
    }

    //模拟一次实际工作，输出结果
    public static void testAwork(){
        initSys();

        for (int i = 0;i < 8;i++){
            work(list[i],true,false);
        }
        while (time < 3600 * 8){
            CNC c = getFreeCNCByChoose();
            if (c != null){
                work(c,true,false);
            }else {
                time += 1;
            }
        }
    }

    //模拟一次工作，获取8小时加工的产品数量
    public static int test(){
        initSys();
        //记录每一次的选择
        List<Integer> choose = new ArrayList<>();
        for (int i = 0;i < 8;i++){
            work(list[i],false,false);
        }
        while (time < 3600 * 8){
            CNC c = getAFreeCNC(choose);
            if (c != null){
                work(c,false,false);
            }else {
                time += 1;
            }
        }

        myChoose = choose;
        return rgv.getNum();
    }

    //系统完成一次工作
    public static void work(CNC c,boolean isOutput,boolean testBad){
        time += rgv.moveTo(c.getPoint());
        time += rgv.AddMaterial(c,time,isOutput,testBad);
        time += rgv.wash();
    }

    //随机决策
    public static CNC getAFreeCNC(List<Integer> choose){
        int n = 0;
        List<CNC> l = new ArrayList<>();
        for (int i = 0;i < 8;i++){
            if (list[i].isWork(time) == false) {
                l.add(list[i]);
                n++;
            }
        }
        if(n != 0){
            int j = (int)(Math.random() * n);
            choose.add(j);
            return l.get(j);
        }
        return null;
    }

    //通过已记录的选择进行决策
    public static CNC getFreeCNCByChoose(){
        int n = 0;
        List<CNC> l = new ArrayList<>();
        for (int i = 0;i < 8;i++){
            if (list[i].isWork(time) == false) {
                l.add(list[i]);
                n++;
            }
        }
        if(n != 0){
            int j = myChoose.get(chooseTime);
            chooseTime++;

            if(l.get(j).isBad(time)){
                l.remove(j);
            }
            return l.get(j%l.size());
        }
        return null;
    }

    //初始化系统
    public static void initSys(){
        Package.init();
        time = 0;
        int[] times = {20,33,46};
        rgv = new RGV(1,25,times);
        list = new CNC[8];
        list[0] = new CNC(1,1,560,true,28);
        list[1] = new CNC(2,1,560,false,31);
        list[2] = new CNC(3,2,560,true,28);
        list[3] = new CNC(4,2,560,false,31);
        list[4] = new CNC(5,3,560,true,28);
        list[5] = new CNC(6,3,560,false,31);
        list[6] = new CNC(7,4,560,true,28);
        list[7] = new CNC(8,4,560,false,31);
    }
}
