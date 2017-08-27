import Object.Route;
import Object.SAVE_TIME;
import Object.Bicycle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class PutuoRoute {
    private static int flag=0;
    private static int time=0;
    private static String timeFlag="1708270930_1130";
    private static final String sql_fix="SELECT * FROM ofo.putuo_mobike_address4 where flag='170827_4' and save_time=";
    private static String[] save_time1=new SAVE_TIME().getSAVE_TIME1();
    private static HashMap<String,Bicycle> Map0=new HashMap<String,Bicycle>();
    private static HashMap<String,Bicycle> Map1=new HashMap<String,Bicycle>();
    private static HashMap<String,Bicycle> getNow(int flag){
        if(flag==0){
            return Map0;
        }
        else{
            return Map1;
        }
    }
    private static HashMap<String,Bicycle> getOld(int flag){
        if(flag==0) {
            return Map1;
        }
        else{
            return Map0;
        }
    }
    private static boolean equalBicycle(Bicycle old,Bicycle now){
        boolean result=true;
        if(!old.getDistX().equals(now.getDistX()))
            result=false;
        if(!old.getDistY().equals(now.getDistY()))
            result=false;
        return result;
    }
    private static void goTime(){
        time++;
        if(flag==0){
            flag=1;
        }
        else{
            flag=0;
        }
        System.out.println(time);
    }
    public static void main(String[] args) {
        HashMap<String,Bicycle> nowMap;
        HashMap<String,Bicycle> oldMap;
        HashMap<String,Bicycle> riding=new HashMap<>();
        ArrayList<String> waitClear=new ArrayList<>();
        MysqlHandler mysqlHandler = new MysqlHandler();
        try{
            Connection connection = mysqlHandler.getConnection();
            Statement statement=connection.createStatement();
            for(int i=0;i<save_time1.length;i++){
                String sql=sql_fix+"\'"+save_time1[i]+"\'";
                System.out.println("获取当前时间车子列表");
                ResultSet resultSet=statement.executeQuery(sql);
                nowMap=getNow(flag);
                oldMap=getOld(flag);
                nowMap.clear();
                while (resultSet.next()){
                    String distX=resultSet.getString("distX");
                    String distY=resultSet.getString("distY");
                    String id=resultSet.getString("bikeIds");
                    Bicycle bicycle=new Bicycle(distX,distY,id,save_time1[i],time);
                    nowMap.put(id,bicycle);
                }
                if (time>1){
                    waitClear.clear();
                    Iterator iterator=riding.entrySet().iterator();
                    System.out.println("检测现在是否有车子被停放");
                    while (iterator.hasNext()){
                        Map.Entry entry=(Map.Entry) iterator.next();
                        String key=(String)entry.getKey();
                        if(nowMap.containsKey(key)){
                            Bicycle old_value=(Bicycle)entry.getValue();
                            Bicycle now_value=(Bicycle)nowMap.get(key);
                            if(!equalBicycle(old_value,now_value)){
                                Route route=new Route(old_value,now_value);
                                waitClear.add(key);
                                route.enterSQL(timeFlag);
                            }else{
                                waitClear.add(key);
                            }
                        }

                    }
                    for(int j=0;j<waitClear.size();j++){
                        riding.remove(waitClear.get(j));
                    }
                }
                if (time>0){
                    Iterator iterable=oldMap.entrySet().iterator();
                    System.out.println("检测3分钟前是否有车子被骑走");
                    while(iterable.hasNext()){
                        Map.Entry entry=(Map.Entry) iterable.next();
                        String key=(String)entry.getKey();
                        if(!nowMap.containsKey(key)){
                            Bicycle value=(Bicycle)entry.getValue();
                            riding.put(key,value);
                        }
                    }
                }
                goTime();
                System.out.println("即将进入下一个时间");
            }
            System.out.println("ok");
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
