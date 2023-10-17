
// 包含SoftwareSerial库，用于创建软件串口 
#include <SoftwareSerial.h>

// 定义串口引脚 
#define RX 8
#define TX 7

// 创建一个软件串口对象 
SoftwareSerial bluetooth(RX, TX);

void setup() {
  // 初始化串口 
  Serial.begin(9600); 
  bluetooth.begin(9600); 
  }

void loop() {
  // 向已连接的蓝牙设备发送"hello"信息 
  bluetooth.println("Front:");
  bluetooth.println("60");//数字
  bluetooth.println("STOP");
  bluetooth.println("Left:");
  bluetooth.println("60");//数字
  bluetooth.println("Right:");
  bluetooth.println("60");//数字
  bluetooth.println("LEFT");
  bluetooth.println("FRONT");
  bluetooth.println("RIGHT");
  bluetooth.println("BACK");

  // 等待一秒 
  delay(10000); 
  }