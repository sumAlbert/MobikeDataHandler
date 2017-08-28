package Object;

import java.util.ArrayList;
import java.util.HashMap;

public class SubwayHelper {
    private String[] subwayEn=new String[20];
    private String[] subwayCn=new String[] {"丰庄","祁连山南路","真北路","大渡河路","金沙江路","隆德路","武宁路","长寿路","江宁路"
            ,"曹杨路","镇坪路","中潭路","枫桥路","真如","李子园","祁连山路","武威路","桃浦新村","岚皋路","新村路"};
    private int info_all1=0;
    private int info_all2=0;
    private int info_all3=0;
    private int info_all4=0;
    private int info_all5=0;
    private HashMap<String,Integer> info1=new HashMap<>();
    private HashMap<String,Integer> info2=new HashMap<>();
    private HashMap<String,Integer> info3=new HashMap<>();
    private HashMap<String,Integer> info4=new HashMap<>();
    private HashMap<String,Integer> info5=new HashMap<>();
    public SubwayHelper(){
        for(int i=0;i<subwayCn.length;i++){
            subwayEn[i]="sub"+String.valueOf(i);
        }
        for(int i=0;i<subwayEn.length;i++){
            info1.put(subwayEn[i],0);
            info2.put(subwayEn[i],0);
            info3.put(subwayEn[i],0);
            info4.put(subwayEn[i],0);
            info5.put(subwayEn[i],0);
        }
    }
    public int getInfo_all1(){
        return this.info_all1;
    }
    public int getInfo_all2(){
        return this.info_all2;
    }
    public int getInfo_all3(){
        return this.info_all3;
    }
    public int getInfo_all4(){
        return this.info_all4;
    }
    public int getInfo_all5(){
        return this.info_all5;
    }
    public HashMap<String, Integer> getInfo1() {
        return info1;
    }
    public HashMap<String, Integer> getInfo2() {
        return info2;
    }
    public HashMap<String, Integer> getInfo3() {
        return info3;
    }
    public HashMap<String, Integer> getInfo4() {
        return info4;
    }
    public HashMap<String, Integer> getInfo5() {
        return info5;
    }
    public String[] getSubwayEn(){
        return this.subwayEn;
    }
    public String[] getSubwayCn(){
        return this.subwayCn;
    }
}
