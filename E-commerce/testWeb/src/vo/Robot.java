package vo;
public class Robot {
        private int id;
        private String name;
        private String color;
        private int numOfAxes;
        private int numOfWheel;
        private String power;
        private int userId;
        private double avgruntime;
        private String user_name;

        public Robot() {
        }

        public Robot(int id, String name, String color, int numOfAxes, int numOfWheel, String power, int userId,String user_name) {
            this.id = id;
            this.name = name;
            this.color = color;
            this.numOfAxes = numOfAxes;
            this.numOfWheel = numOfWheel;
            this.power = power;
            this.userId = userId;
            this.user_name = user_name;
        }

        // Getter and Setter methods

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getNumOfAxes() {
            return numOfAxes;
        }

        public void setNumOfAxes(int numOfAxes) {
            this.numOfAxes = numOfAxes;
        }

        public int getNumOfWheel() {
            return numOfWheel;
        }

        public void setNumOfWheel(int numOfWheel) {
            this.numOfWheel = numOfWheel;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public double getAvgruntime() { // 添加 avgruntime 的 getter 方法
        return avgruntime;
        }
        public void setAvgruntime(double avgruntime) { // 添加 avgruntime 的 setter 方法
            this.avgruntime = avgruntime;
        }
        public void setUserName(String user_name){this.user_name = user_name;};
        public String getUserName(){return user_name;}
}

