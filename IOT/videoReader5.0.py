import tkinter as tk
from tkinter import filedialog
from ultralytics import YOLO
from moviepy.editor import VideoFileClip
import os
import shutil
import cv2
def convert_avi_to_mp4(avi_file, mp4_file):
    # Load the video
    video = VideoFileClip(avi_file)

    # Convert the video to MP4 format
    video.write_videofile(mp4_file)

    # Return the URL of the MP4 file
    return mp4_file

#the calculator of video length
def get_video_length(filename):
    # Create a VideoCapture object
    video = cv2.VideoCapture(filename)

    # get fps of videos
    frame_count = video.get(cv2.CAP_PROP_FRAME_COUNT)
    fps = video.get(cv2.CAP_PROP_FPS)

    # calculate the length
    length_in_seconds = frame_count / fps

    # transform the length to string
    if length_in_seconds < 60:
        length_string = f"{length_in_seconds:.2f}"
    elif length_in_seconds < 3600:
        minutes = int(length_in_seconds // 60)
        seconds = int(length_in_seconds % 60)
        length_string = f"{minutes}{seconds}"
    else:
        hours = int(length_in_seconds // 3600)
        minutes = int((length_in_seconds % 3600) // 60)
        seconds = int(length_in_seconds % 60)
        length_string = f"{hours}{minutes}{seconds}"

    return length_string



def select_folder():
    folder_path = filedialog.askdirectory()
    folder_path_entry.delete(0, tk.END)
    folder_path_entry.insert(0, folder_path)


def start_detection():
    # get folder path
    folder_path2 = folder_path_entry.get()

    # get all files in the folder
    files = os.listdir(folder_path2)

    # if the num of files larger than 1,then delete the earlier one
    if len(files) > 1:
        # get the time each file has been changed at last
        file_times = [(f, os.path.getmtime(os.path.join(folder_path2, f))) for f in files]
        # sort by the last changing time
        file_times.sort(key=lambda x: x[1])
        # delete the earlier one
        os.remove(os.path.join(folder_path2, file_times[0][0]))


    model = YOLO("D:\\pythonProject4\\runs\\detect\\train12\\weights\\best.pt")
    results = model.predict(source=folder_path_entry.get(),save=True, classes=[0, 1, 2], conf=0.4)



    # get the folder path
    folder_path = "D:\\pythonProject4\\runs\\detect\\predict"
    # open it
    os.startfile(folder_path)
    # get all files in it
    files = os.listdir(folder_path)
    # get the first one
    old_file_path = os.path.join(folder_path, files[0])
    # copy it to the path below
    target_folder = "D:\\pythonProject4\\video\\"
    shutil.copy(old_file_path, target_folder)
    # delete the folder path
    shutil.rmtree(folder_path)

    # calculate the num of files in the folder
    file_count = len([f for f in os.listdir(target_folder) if os.path.isfile(os.path.join(target_folder, f))])

    # rename the file
    new_file_name = str(file_count-2) + ".avi"
    new_file_path = os.path.join(target_folder, new_file_name)
    os.rename(os.path.join(target_folder, files[0]), new_file_path)
    mp4_file = 'D:\\pythonProject4\\video\\' + str(file_count-3) + ".mp4"
    mp4_url = convert_avi_to_mp4(new_file_path , mp4_file)


    # Writing the path of the file to a text file
    relative_path = os.path.relpath(mp4_file, "D:\\pythonProject4\\video")
    with open(r"D:\pythonProject4\video\videoURL.txt", "a") as f:
        f.write("./Videos/" + relative_path + "\n")

    # Writing the runtime of the file to a text file
    with open(r"D:\pythonProject4\video\runtime.txt", "a") as f:
        f.write(get_video_length(mp4_file) + "\n")

    os.remove(new_file_path)
    print(results.xyxy)
    status_label.config(text="Done")


root = tk.Tk()
root.title("YOLOv8 Vedio Detection")
root.geometry("400x250")

folder_path_label = tk.Label(root, text="Folder Path：")
folder_path_label.grid(row=0, column=0)

folder_path_entry = tk.Entry(root)
folder_path_entry.grid(row=0, column=1)

select_folder_button = tk.Button(root, text="Choose", command=select_folder)
select_folder_button.grid(row=0, column=2)

start_detection_button = tk.Button(root, text="Start Detecting", command=start_detection)
start_detection_button.grid(row=2, columnspan=3)

status_label = tk.Label(root, text="")
status_label.grid(row=3, columnspan=3)

close_button = tk.Button(root, text="Close the Program", command=root.destroy)
close_button.grid(row=4, columnspan=3)

root.mainloop()
