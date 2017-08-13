import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResultHandler {
    private static final String PATH="C:\\Users\\98267\\IdeaProjects\\spider2\\src\\Data\\ershoufangresult";
    public static void main(String[] args){
        for(int page=53;page<54;page++) {
            String TOL_PATH = PATH+String.valueOf(page)+".txt";
            File file = new File(TOL_PATH);
            if (file.exists() || file.isFile()) {
                try {
                    MysqlHandler mysqlHandler = new MysqlHandler();
                    Connection connection = mysqlHandler.getConnection();
                    InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line = bufferedReader.readLine();
                    String jsonline = "";
                    while (line != null) {
                        jsonline = jsonline + line;
                        line = bufferedReader.readLine();
                    }
                    jsonline = jsonline.substring(0, jsonline.length() - 1) + "}";
                    JSONObject jsonObject = JSONObject.fromObject(jsonline);
                    Iterator<String> iterable = jsonObject.keys();
                    String keys_sql = "(";
                    String values_sql = "(";
                    List<String> list = new ArrayList<String>();
                    while (iterable.hasNext()) {
                        String url = (String) iterable.next();
                        JSONObject url_json = jsonObject.getJSONObject(url);
                        keys_sql = keys_sql + "ofo_url";
                        values_sql = values_sql + "'" + url + "'";
                        String tag_sql = "('" + url + "'";
                        Iterator iterable1_twice = url_json.keys();
                        while (iterable1_twice.hasNext()) {
                            String key = (String) iterable1_twice.next();
                            if (!key.equals("tag_item")) {
                                String value = (String) url_json.get(key);
                                keys_sql = keys_sql + "," + key;
                                values_sql = values_sql + ",'" + value + "'";
                            } else {
                                JSONArray jsonArray = url_json.getJSONArray(key);
                                for (int i = 0; i < jsonArray.size(); i++) {
                                    list.add(jsonArray.getString(i));
                                }
                            }
                        }
                        keys_sql = keys_sql + ")";
                        values_sql = values_sql + ")";
                        Statement stmt1 = connection.createStatement();
                        String sql1 = "insert into ofo_info " + keys_sql + " values " + values_sql + ";";
                        stmt1.execute(sql1);
                        stmt1.close();
                        for (int j = 0; j < list.size(); j++) {
                            Statement stmt2 = connection.createStatement();
                            String sql2 = "insert into tag_info values " + tag_sql + ",'" + list.get(j) + "')";
                            stmt2.execute(sql2);
                            stmt2.close();
                        }
                        keys_sql = "(";
                        values_sql = "(";
                        list.clear();
                    }
                    bufferedReader.close();
                    inputStreamReader.close();
                } catch (Exception exception) {
                    System.out.println(page);
                    exception.printStackTrace();
                }
            } else {
                System.out.println("It's not a file");
            }
        }
    }
}
