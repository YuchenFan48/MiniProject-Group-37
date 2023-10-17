package vo;
import java.time.LocalDateTime;
public class Rec {
    private int user_id;
    private int robot_id;
    private int rec_id;
    private int runtime;//seconds
    private int status;//wether find treasure & treasure type
    private String videoURL;

    public Rec(){

    }
    public Rec(int user_id,int robot_id,int rec_id,int runtime,int status,String videoURL){
        this.user_id = user_id;
        this.robot_id = robot_id;
        this.rec_id = rec_id;
        this.runtime = runtime;
        this.videoURL = videoURL;
        this.status = status;
    }
    // Getters
    public int getUserId() {
        return user_id;
    }

    public int getRobotId() {
        return robot_id;
    }

    public int getRecId() {
        return rec_id;
    }

    public int getRuntime() {
        return runtime;
    }
    public int getStatus(){
        return status;
    }

    public String getVideoURL() {
        return videoURL;
    }

    // Setters
    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public void setRobotId(int robot_id) {
        this.robot_id = robot_id;
    }

    public void setRecId(int rec_id) {
        this.rec_id = rec_id;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
}
