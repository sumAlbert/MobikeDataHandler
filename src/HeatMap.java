import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HeatMap {
    public static void main(String[] args){
        MysqlHandler mysqlHandler=new MysqlHandler();
        Connection connection=mysqlHandler.getConnection();
        String sql="select * from ofo_address";
        File file=new File("C:\\Users\\98267\\IdeaProjects\\spider2\\src\\heatmap_170806.txt");
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
