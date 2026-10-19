# PhoneDetect

## Ubiquitous Computing - Lab 3

PhoneDetect is an Android application developed in Java using Android Studio.

The application monitors the device accelerometer and detects when significant movement occurs. When movement is detected, a notification is generated containing the time of the detected motion. Selecting the notification opens a details screen displaying the motion information.

---

## Features

- Background service monitoring device motion
- Accelerometer sensor integration
- Motion detection using sensor values
- Android notifications
- Motion details activity
- Developed using Java
- Version controlled using Git and GitHub

---

## Technologies Used

- Android Studio
- Java
- Android Services
- Accelerometer Sensor
- Notifications
- GitHub

---

## Project Structure

```text
PhoneDetect
│
├── MainActivity.java
├── MotionDetectionService.java
├── MotionDetailsActivity.java
│
├── activity_main.xml
├── activity_motion_details.xml
│
└── AndroidManifest.xml
```

---

## How It Works

1. The user launches the application.
2. The user presses the Start Monitoring button.
3. The background service begins monitoring accelerometer values.
4. Significant movement is detected.
5. A notification is generated.
6. The user selects the notification.
7. Motion details are displayed.

---

## Author

GitHub: SpongeBoy128

Module: Ubiquitous Computing

Lab: Lab 3 - Don't Touch My Phone
