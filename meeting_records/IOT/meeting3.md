# Meeting Minutes 3: Local file content upload database code implementation


**Date:** September 26, 2023
**Time:** 19:05
**Location:** Online
**Meeting Topic:** Local file content upload database code implementation
**Attendees:** Lyu Mingyi, Zhang Yunzhong, Su Yongyuan

## Summary
In order to write the code for the IoT recognizer into the database, we first need to get the URL and runtime for each video generated at the end of the running program, then write a piece of code to write the URL and runtime to the SQL database each time you run the program, so you can open the web site to see the video that identifies the generated video.
At the same time, we ran into a problem. avi video files couldn't be played by Web sites, so we needed to solve this problem with a plugin called moviepy; after each video was generated, we converted it to. .mp4 format, and then write down the .mp4 format video URL to the local txt document
