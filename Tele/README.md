# Repository Contents

- **CA_BT:** 
  - Description: Code for obstacle avoidance combined with Bluetooth transmission.
- **CA:** 
  - Description: Code solely for obstacle avoidance.
- **BT:**
  - Description: Code solely for Bluetooth transmission.

# Bluetooth-Controlled Car Documentation

## Overview
This document provides a comprehensive technical overview of a Bluetooth-controlled car system. The system makes use of the Arduino platform, a servo for steering, an HC-SR04 ultrasonic sensor for distance measurement, a matrix LED display, and two motors for car movement. Commands are received via a Bluetooth module, enabling wireless control.

---

## Components:
1. **Arduino Microcontroller**: Core component for logic and control.
2. **HC-SR04 Ultrasonic Sensor**: Measures distance by sending and receiving ultrasonic waves.
3. **Servo Motor**: Adjusts direction by varying angles.
4. **Matrix LED Display**: Displays various patterns corresponding to the car's actions.
5. **2x DC Motors**: Drive the car forward, backward, left, or right.
6. **Bluetooth Module (SoftwareSerial)**: Facilitates wireless communication.

---

## Pin Configuration:
- **Matrix Display**: SCL_Pin -> A5, SDA_Pin -> A4
- **Ultrasonic Sensor**: trigPin -> D12, echoPin -> D13
- **Motor A**: Direction -> D2, Speed (PWM) -> D6
- **Motor B**: Direction -> D4, Speed (PWM) -> D5
- **Servo Motor**: D10
- **Bluetooth Module**: RX -> D7, TX -> D8

---

## Key Functions:
1. **get_distance() -> int**
   - **Purpose**: Obtains the distance reading from the ultrasonic sensor.
   - **Logic**: Sends a trigger pulse, listens for an echo, and computes the time taken for the pulse to reflect back. Distance is then calculated using the speed of sound.
2. **Motion Functions**:
   - **advance()**: Moves the car forward.
   - **turnL()**: Turns the car to the left.
   - **turnR()**: Turns the car to the right.
   - **stopp()**: Halts all motion.
3. **matrix_display(unsigned char matrix_value[])**
   - **Purpose**: Displays patterns on the LED matrix.
   - **Logic**: Utilizes the I2C communication protocol, receiving an array of byte values representing the pattern to be displayed.
4. **I2C Communication**:
   - **IIC_start()**: Initiates data transmission.
   - **IIC_send(unsigned char send_data)**: Sends a byte over I2C.
   - **IIC_end()**: Terminates data transmission.

---

## Operational Flow:
1. Initialize system components.
2. In each loop iteration, measure the distance in front of the car.
3. Based on the distance:
   - If an obstacle is closer than 15cm, the car stops and checks distances to its left and right.
   - It then chooses a direction (left, right, or back) based on available distances.
   - Displays corresponding patterns on the matrix display and relays information over Bluetooth.
4. If there are no nearby obstacles, the car moves forward.

---

## Enhancements:
1. **Error Handling**: Integrate error handling mechanisms for scenarios such as Bluetooth disconnection or sensor malfunctions.
2. **Battery Monitoring**: Implement a battery monitoring system to warn when battery levels are low.
3. **Feedback Mechanism**: Introduce a feedback mechanism for the user, communicating the status or any issues with the car.


# ESP32 Camera Control for Self-Driven Car

## Introduction:
The provided code allows the user to control a camera module using the ESP32. This is useful for self-driven cars, as it provides a visual input for the car's software to process and make decisions based on. Below is a guide on how to use and understand the code.

---

### 1. Prerequisites:
   - **ESP32 module with PSRAM**: Required for high-resolution and quality images.
   - **ESP32 camera module**: Compatible models are mentioned in the code.
   - **WiFi access**: To connect ESP32 for data transmission.

---

### 2. Selecting a Camera Model:
Before using the camera, make sure to select the right model by uncommenting the respective `#define` line. For instance, the provided code has `CAMERA_MODEL_ESP_EYE` uncommented, which means this is the camera model in use.

---

### 3. WiFi Credentials:
Replace `****` for both `ssid` and `password` with your WiFi's SSID and password. This will allow ESP32 to connect to your network and transmit the camera data.

---

### 4. Setting up the Camera:
The `setup()` function initializes the camera with the following configurations:
   - **LEDC Channel & Timer**: Used for the camera's LED control.
   - **Pin Configuration**: Various pins such as D0, D1, etc., are assigned to interface with the camera module. These pins are defined in the `camera_pins.h` file and are specific to the selected camera model.   
   *Note*: The code provided seems to be cut off after `config.pin_d1`. Ensure that the rest of the pins and camera configurations are defined.

---

### 5. Starting the Camera Server:
The `startCameraServer();` function, when called, starts the camera server, allowing for the capturing and transmission of images.

---

### 6. Additional Features:
   - **Flash LED Setup**: The `setupLedFlash(int pin);` function can be used to control the LED flash of the camera. The specific pin to which the LED is connected should be passed as an argument.

---

### 7. Debugging:
The Serial monitor is extensively used for debugging. Ensure the baud rate is set to `115200` to view the logs. If issues arise, the logs can provide valuable information to pinpoint the problem.

---

### 8. Important Notes:
   - Ensure you've selected a partition scheme from the board menu in your IDE that provides at least 3MB of APP space. This is necessary to accommodate the camera library and functions.
   - While face recognition is disabled for certain ESP32 models due to processing time, face detection can be enabled if PSRAM is present.

![Car Image](car1.png)
_Image Description: Car_


