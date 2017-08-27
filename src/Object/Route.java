package Object;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Route {
    private int start_time;
    private int stop_time;
    private String start_lng;
    private String start_lat;
    private String stop_lng;
    private String stop_lat;
    private String id;
    private int interval;
    public Route(Bicycle old,Bicycle now){
        start_lng=old.getDistX();
        start_lat=old.getDistY();
        start_time=old.getTime();
        stop_lng=now.getDistX();
        stop_lat=now.getDistY();
        stop_time=now.getTime();
        id=old.getId();
        interval=stop_time-start_time;
    }
    public void setInterval(int interval){
        this.interval=interval;
    }
    public void setId(String id){
        this.id=id;
    }
    public void setStart_lng(String start_lng){
        this.start_lng=start_lng;
    }
    public void setStart_lat(String start_lat){
        this.start_lat=start_lat;
    }
    public void setStop_lng(String stop_lat){
        this.stop_lat=stop_lat;
    }
    public void setStop_lat(String stop_lat){
        this.stop_lat=stop_lat;
    }
    public void setStart_time(int start_time){
        this.start_time=start_time;
    }
    public void setStop_time(int stop_time){
        this.stop_time=stop_time;
    }
    public String getStart_lng(){
        return this.start_lng;
    }
    public String getStart_lat(){
        return this.start_lat;
    }
    public String getStop_lng(){
        return this.stop_lng;
    }
    public String getStop_lat(){
        return this.stop_lat;
    }
    public String getId(){
        return this.id;
    }
    public int getInterval(){
        return this.interval;
    }
    public int getStart_time(){
        return this.start_time;
    }
    public int getStop_time(){
        return this.stop_time;
    }
    public void enterSQL(String flag){
        MysqlHandler mysqlHandler=new MysqlHandler();
        Connection connection=mysqlHandler.getConnection();
        try {
            Statement statement=connection.createStatement();
            String sql="insert into putuo_mobike_route (start_distX,start_distY,stop_distX,stop_distY,bikeIds,inteval,flag) values (\'"+start_lng+"\',\'"+start_lat+"\',\'"+stop_lng+"\',\'"+stop_lat+"\',\'"+id+"\',"+interval*3+",\'"+flag+"\')";
            statement.execute(sql);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
