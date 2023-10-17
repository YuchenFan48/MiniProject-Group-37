#include <Servo.h>
#include <SoftwareSerial.h>
Servo myservo;  // create servo object to control a servo
//数组，用于储存图案的数据，可以自己算也可以从取摸工具中得到
unsigned char front[] = {0x00, 0x00, 0x00, 0x00, 0x00, 0x24, 0x12, 0x09, 0x12, 0x24, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
unsigned char left[] = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x44, 0x28, 0x10, 0x44, 0x28, 0x10, 0x44, 0x28, 0x10, 0x00};
unsigned char right[] = {0x00, 0x10, 0x28, 0x44, 0x10, 0x28, 0x44, 0x10, 0x28, 0x44, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
unsigned char STOP01[] = {0x2E, 0x2A, 0x3A, 0x00, 0x02, 0x3E, 0x02, 0x00, 0x3E, 0x22, 0x3E, 0x00, 0x3E, 0x0A, 0x0E, 0x00};
unsigned char clear[] = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
unsigned char back[] = {0x00, 0x00, 0x00, 0x00, 0x00, 0x24, 0x48, 0x90, 0x48, 0x24, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
#define SCL_Pin  A5  //设置时钟引脚为 A5
#define SDA_Pin  A4  //设置数据引脚为 A4
int trigPin = 12;  //定义TRIG引脚接D12
int echoPin = 13;   //定义ECHO引脚接D13
int distance, distance_l, distance_r;
int MA = 2; //定义电机A方向控制引脚为D2
int PWMA = 6; //定义电机A速度控制引脚为D6
int MB = 4; //定义电机A方向控制引脚为D4
int PWMB = 5; //定义电机A速度控制引脚为D5
#define RX 7 
#define TX 8 // 定义蓝牙串口引脚
SoftwareSerial bluetooth(RX, TX); // 创建一个软件串口对象

void setup ()
{
  Serial.begin(9600);     //测量结果将通过此串口输出至 PC 上的串口监视器
  bluetooth.begin(9600); 
  myservo.attach(10);  // attaches the servo on pin 10 to the servo object
  pinMode(echoPin, INPUT);      //设置EchoPin 为输入模式
  pinMode(trigPin, OUTPUT);     //设置超声波数字IO脚模式，OUTPUT为输出
  pinMode(MA, OUTPUT); //配置电机引脚为输出模式
  pinMode(PWMA, OUTPUT);
  pinMode(MB, OUTPUT);
  pinMode(PWMB, OUTPUT);
  pinMode(SCL_Pin, OUTPUT); //  设置时钟引脚为输出
  pinMode(SDA_Pin, OUTPUT); //设置数据引脚为输出
  matrix_display(clear);// 清屏
  myservo.write(90);  //舵机角度为90
  delay(500);
}

void loop()
{
  Serial.print("Front:");
  bluetooth.println("Front:");
  distance = get_distance(); //调用测距函数

  if (distance > 0 && distance < 15) { //如果距离小于20且大于0
    stopp();//停止
    matrix_display(STOP01);   //点阵显示停止图案
    bluetooth.println("STOP");
    Serial.print("Left:");
    bluetooth.println("Left:");
    delay(100);
    myservo.write(180); //舵机转到180度
    delay(500);
    distance_l = get_distance(); //获取左边的距离

    
    Serial.print("Right:");
    bluetooth.println("Right:");
    delay(100);
    myservo.write(0); //舵机转到0度
    delay(500);
    distance_r = get_distance(); //获取右边的距离
    

    delay(500);
    if (distance_l > 15) { //比较距离，如果左边大于右边
      turnL();  //向左转
      matrix_display(left);   //点阵显示向左图案
      bluetooth.println("LEFT");
      myservo.write(90);//舵机回到90度
      delay(500);
      matrix_display(front);   //点阵显示前进图案
      bluetooth.println("FRONT");
    }
    else if(distance_r > 15) { //否则如果右边大于左边
      turnR();//向右转
      matrix_display(right);   //显示右转图案
      bluetooth.println("RIGHT");
      myservo.write(90);//舵机回到90度
      delay(500);
      matrix_display(front);   //显示前进图案
      bluetooth.println("FRONT");
    }
    else {
      turnR();//向右转
      turnR();//向右转
      matrix_display(back);   //显示右转图案
      bluetooth.println("BACK");
      delay(1000);
      myservo.write(90);//舵机回到90度
      matrix_display(front);   //显示前进图案
      bluetooth.println("FRONT");
    }
  }

  else { //前方距离大于等于10cm时
    advance();//前进
    matrix_display(front);   //显示前进图案
    bluetooth.println("FRONT");
  }

}

int get_distance() {
  int distance = 0;
  digitalWrite(trigPin, LOW);     // 通过Trig/Pin 发送脉冲，触发 HC-SR04 测距，使发出发出超声波信号接口低电平2μs
  delayMicroseconds(2);
  digitalWrite(trigPin, HIGH);    // 使发出发出超声波信号接口高电平10μs，这里是至少10μs
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);     // 保持发出超声波信号接口低电平
  distance = pulseIn(echoPin, HIGH) / 58; // 读出脉冲时间,将脉冲时间转化为距离（单位：厘米）
  Serial.println(distance);        //输出距离值
  bluetooth.println(distance);
  return distance;
}

void advance() { //小车前进
  digitalWrite(MA, LOW); //电机A正转
  analogWrite(PWMA, 150); //电机A速度为150
  digitalWrite(MB, HIGH); //电机B正转
  analogWrite(PWMB, 150); //电机B速度为150
}

/*
void back() { //小车后退
  digitalWrite(MA, HIGH); //电机A反转
  analogWrite(PWMA, 150); //电机A速度为150
  digitalWrite(MB, LOW); //电机B反转
  analogWrite(PWMB, 150); //电机B速度为150
}
*/

void turnL() { //小车左转
  digitalWrite(MA, HIGH); //电机A反转
  analogWrite(PWMA, 150); //电机A速度为150
  digitalWrite(MB, HIGH); //电机B正转
  analogWrite(PWMB, 150); //电机B速度为150
}

void turnR() { //小车右转
  digitalWrite(MA, LOW); //电机A正转
  analogWrite(PWMA, 150); //电机A速度为150
  digitalWrite(MB, LOW); //电机B反转
  analogWrite(PWMB, 150); //电机B速度为150
}

void stopp() { //小车停止
  analogWrite(PWMA, 0); //电机A速度为0
  analogWrite(PWMB, 0); //电机B速度为0
}

//这个函数用于点阵屏显示
void matrix_display(unsigned char matrix_value[])
{
  IIC_start();  //调用数据传输开始条件的函数
  IIC_send(0xc0);  //选择地址
  for (int i = 0; i < 16; i++) //图案数据有16个字节
  {
    IIC_send(matrix_value[i]); //传输图案的数据
  }
  IIC_end();   //结束图案数据传输
  IIC_start();
  IIC_send(0x8A);  //显示控制，选择脉宽为4/16
  IIC_end();
}
//传输数据开始的条件
void IIC_start()
{
  digitalWrite(SCL_Pin, HIGH);
  delayMicroseconds(3);
  digitalWrite(SDA_Pin, HIGH);
  delayMicroseconds(3);
  digitalWrite(SDA_Pin, LOW);
  delayMicroseconds(3);
}
//传输数据
void IIC_send(unsigned char send_data)
{
  for (char i = 0; i < 8; i++) //每个字节有8位
  {
    digitalWrite(SCL_Pin, LOW); //将时钟引脚SCL_Pin拉低，才可以改变SDA的信号
    delayMicroseconds(3);
    if (send_data & 0x01) //根据字节的每一位是1还是0来设置SDA_Pin的高低电平
    {
      digitalWrite(SDA_Pin, HIGH);
    }
    else
    {
      digitalWrite(SDA_Pin, LOW);
    }
    delayMicroseconds(3);
    digitalWrite(SCL_Pin, HIGH); //将时钟引脚SCL_Pin拉高，停止数据的传输
    delayMicroseconds(3);
    send_data = send_data >> 1;  //一位一位的检测，所以将数据右移一位
  }
}
//数据传输结束的标志
void IIC_end()
{
  digitalWrite(SCL_Pin, LOW);
  delayMicroseconds(3);
  digitalWrite(SDA_Pin, LOW);
  delayMicroseconds(3);
  digitalWrite(SCL_Pin, HIGH);
  delayMicroseconds(3);
  digitalWrite(SDA_Pin, HIGH);
  delayMicroseconds(3);
}
