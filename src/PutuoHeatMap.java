import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PutuoHeatMap {
    public static void main(String[] args){
        MysqlHandler mysqlHandler=new MysqlHandler();
        Connection connection=mysqlHandler.getConnection();
//        String sql="SELECT * FROM ofo.putuo_mobike_address2 as t1,ofo.putuo_mobike_address2 as t2 where t1.bikeIds=t2.bikeIds and t1.save_time='2017-08-13 21:00:00' and t2.save_time='2017-08-13 19:00:00' and t1.distX=t2.distX and t1.distY=t2.distY;";
        String sql="SELECT * FROM ofo.putuo_mobike_address2 where save_time = '2017-08-13 21:00:00'";
//        File file=new File("C:\\Users\\98267\\IdeaProjects\\spider2\\src\\putuo_heatmap2_notmove_170813_1900_2100.txt");
        File file=new File("C:\\Users\\98267\\IdeaProjects\\spider2\\src\\putuo_heatmap2_1708132100.txt");
        try {
            PrintStream printStream=new PrintStream(new FileOutputStream(file));
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                String distX=resultSet.getString("distX");
                String distY=resultSet.getString("distY");
                String result="{\"lng\":"+distX.substring(0,13)+",\"lat\":"+distY.substring(0,12)+",\"count\":1},\n";
                printStream.append(result);
            }
            printStream.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
