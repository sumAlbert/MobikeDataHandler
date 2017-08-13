import net.sf.json.JSONArray;

import java.io.*;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BorderHandler {
    public static void main(String[] args){
        try {
            List<String> lngs=new ArrayList<>();
            List<String> lats=new ArrayList<>();
            File file=new File("C:\\Users\\98267\\IdeaProjects\\spider2\\src\\upper.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String dictStr="";
            String line=bufferedReader.readLine();
            while(line!=null){
                dictStr=dictStr+line;
                line=bufferedReader.readLine();
            }
            JSONArray jsonArray=JSONArray.fromObject(dictStr);
            int jsonArray_num=jsonArray.size();
            for(int i=0;i<jsonArray_num;i++){
                lngs.add((jsonArray.getJSONObject(i).getString("lng")+"0000").substring(0,7));
                lats.add(jsonArray.getJSONObject(i).getString("lat").substring(0,6));
            }
            MysqlHandler mysqlHandler=new MysqlHandler();
            Connection connection=mysqlHandler.getConnection();
            Statement statement=connection.createStatement();
            for(int i=0;i<jsonArray_num;i++){
                String sql="insert into shanghai_border_upper values ('"+lngs.get(i)+"','"+lats.get(i)+"')";
                System.out.println(sql);
                statement.execute(sql);
            }
            statement.close();
            bufferedReader.close();
            inputStreamReader.close();
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
