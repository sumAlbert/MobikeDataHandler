package Object;

public class Bicycle {
    private String distX;
    private String distY;
    private String id;
    private int time;
    private String save_time;
    public Bicycle(String distX,String distY,String id,int time){
        super();
        this.distY=distY;
        this.distX=distX;
        this.id=id;
        this.time=time;
        XYaround(3);
    }
    public Bicycle(String distX,String distY,String id,String save_time,int time){
        super();
        this.distY=distY;
        this.distX=distX;
        this.id=id;
        this.time=time;
        this.save_time=save_time;
        XYaround(3);
    }
    public void setDistX(String distX){
        this.distX=distX;
    }
    public void setDistY(String distY){
        this.distY=distY;
    }
    public void setId(String id){
        this.id=id;
    }
    public void setTime(int time){
        this.time=time;
    }
    public void setSave_time(String save_time){
        this.save_time=save_time;
    }
    public String getDistX(){
        return this.distX;
    }
    public String getDistY(){
        return this.distY;
    }
    public String getId(){
        return this.id;
    }
    public int getTime(){
        return this.time;
    }
    public String getSave_time(){
        return this.save_time;
    }
    public void XYaround(int num){
        this.distX=this.distX.substring(0,4+num);
        this.distY=this.distY.substring(0,3+num);
    }
}
