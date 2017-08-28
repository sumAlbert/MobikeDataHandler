import com.mysql.jdbc.Driver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import Object.SubwayHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

//对ofo到普陀区地铁站的距离进行整理
public class PutuoSubwayDistance {
    private static String[] subwayEn;
    private static String[] subwayCn;
    private static int least;
    private static int info_all1;
    private static int info_all2;
    private static int info_all3;
    private static int info_all4;
    private static int info_all5;
    private static HashMap<String,Integer> info1=new HashMap<>();
    private static HashMap<String,Integer> info2=new HashMap<>();
    private static HashMap<String,Integer> info3=new HashMap<>();
    private static HashMap<String,Integer> info4=new HashMap<>();
    private static HashMap<String,Integer> info5=new HashMap<>();
    private static void getDistance(int subway,String distance){
        int result;
        if(distance.length()==3){
            result=1;
        }
        else{
            char first=distance.charAt(0);
            switch (first){
                case '1':
                    result=2;
                    break;
                case '2':
                    result=3;
                    break;
                case '3':
                    result=4;
                    break;
                case '4':
                    result=5;
                    break;
                default:
                    result=0;
                    break;
            }
        }
        if(result!=0){
            if(least>result){
                least=result;
            }
        }
        switch (result){
            case 1:{
                info1.put(subwayEn[subway],info1.get(subwayEn[subway])+1);
                info2.put(subwayEn[subway],info2.get(subwayEn[subway])+1);
                info3.put(subwayEn[subway],info3.get(subwayEn[subway])+1);
                info4.put(subwayEn[subway],info4.get(subwayEn[subway])+1);
                info5.put(subwayEn[subway],info5.get(subwayEn[subway])+1);
                break;
            }
            case 2:{
                info2.put(subwayEn[subway],info2.get(subwayEn[subway])+1);
                info3.put(subwayEn[subway],info3.get(subwayEn[subway])+1);
                info4.put(subwayEn[subway],info4.get(subwayEn[subway])+1);
                info5.put(subwayEn[subway],info5.get(subwayEn[subway])+1);
                break;
            }
            case 3:{
                info3.put(subwayEn[subway],info3.get(subwayEn[subway])+1);
                info4.put(subwayEn[subway],info4.get(subwayEn[subway])+1);
                info5.put(subwayEn[subway],info5.get(subwayEn[subway])+1);
                break;
            }
            case 4:{
                info4.put(subwayEn[subway],info4.get(subwayEn[subway])+1);
                info5.put(subwayEn[subway],info5.get(subwayEn[subway])+1);
                break;
            }
            case 5:{
                info5.put(subwayEn[subway],info5.get(subwayEn[subway])+1);
                break;
            }default:{
                break;
            }
        }
    }
    private static void getTotal(){
        switch (least){
            case 1:{
                info_all1++;
                info_all2++;
                info_all3++;
                info_all4++;
                info_all5++;
                break;
            }
            case 2:{
                info_all2++;
                info_all3++;
                info_all4++;
                info_all5++;
                break;
            }
            case 3:{
                info_all3++;
                info_all4++;
                info_all5++;
                break;
            }
            case 4:{
                info_all4++;
                info_all5++;
                break;
            }
            case 5:{
                info_all5++;
                break;
            }default:{
                break;
            }
        }
    }
    private static void init(){
        SubwayHelper subwayHelper=new SubwayHelper();
        info1=subwayHelper.getInfo1();
        info2=subwayHelper.getInfo2();
        info3=subwayHelper.getInfo3();
        info4=subwayHelper.getInfo4();
        info5=subwayHelper.getInfo5();
        info_all1=subwayHelper.getInfo_all1();
        info_all2=subwayHelper.getInfo_all2();
        info_all3=subwayHelper.getInfo_all3();
        info_all4=subwayHelper.getInfo_all4();
        info_all5=subwayHelper.getInfo_all5();
        subwayEn=subwayHelper.getSubwayEn();
        subwayCn=subwayHelper.getSubwayCn();
    }
    public static void main(String[] args){
        init();
        try{
            Logger logger=Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
            Driver driver=new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/ofo?useUnicode=true&characterEncoding=utf8&useSSL=true","root","123aaaaaa");
            Statement statement=connection.createStatement();
            String sql="select * from putuo_subway_distance";
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                least=6;
                for(int i=0;i<subwayEn.length;i++){
                    String subInfo=resultSet.getString(subwayEn[i]);
                    logger.info(String.valueOf(i));
                    getDistance(i,subInfo);
                }
                getTotal();
            }
            File file=new File("C:\\Users\\98267\\IdeaProjects\\spider2\\src\\Data\\subway\\subway_distance.txt");
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            PrintStream printStream=new PrintStream(fileOutputStream);
            printStream.append(String.valueOf(info_all1)+'\n');
            printStream.append(String.valueOf(info_all2)+'\n');
            printStream.append(String.valueOf(info_all3)+'\n');
            printStream.append(String.valueOf(info_all4)+'\n');
            printStream.append(String.valueOf(info_all5)+'\n');
            printStream.append("1km:\n");
            for(int i=0;i<subwayEn.length;i++){
                if(i!=subwayEn.length-1){
                    printStream.append(info1.get(subwayEn[i]).toString()+',');
                }else{
                    printStream.append(info1.get(subwayEn[i]).toString()+'\n');
                }
            }
            printStream.append("2km:\n");
            for(int i=0;i<subwayEn.length;i++){
                if(i!=subwayEn.length-1){
                    printStream.append(info2.get(subwayEn[i]).toString()+',');
                }else{
                    printStream.append(info2.get(subwayEn[i]).toString()+'\n');
                }
            }
            printStream.append("3km:\n");
            for(int i=0;i<subwayEn.length;i++){
                if(i!=subwayEn.length-1){
                    printStream.append(info3.get(subwayEn[i]).toString()+',');
                }else{
                    printStream.append(info3.get(subwayEn[i]).toString()+'\n');
                }
            }
            printStream.append("4km:\n");
            for(int i=0;i<subwayEn.length;i++){
                if(i!=subwayEn.length-1){
                    printStream.append(info4.get(subwayEn[i]).toString()+',');
                }else{
                    printStream.append(info4.get(subwayEn[i]).toString()+'\n');
                }
            }
            printStream.append("5km:\n");
            for(int i=0;i<subwayEn.length;i++){
                if(i!=subwayEn.length-1){
                    printStream.append(info5.get(subwayEn[i]).toString()+',');
                }else{
                    printStream.append(info5.get(subwayEn[i]).toString()+'\n');
                }
            }
            System.out.println("ok");
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
