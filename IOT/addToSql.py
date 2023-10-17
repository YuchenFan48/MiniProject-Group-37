import os
import pymysql
import tkinter as tk

# get file path
file_path = 'D:\\pythonProject4\\video\\videoURL.txt'

# open the file and read all rows
with open(file_path, 'r') as f:
    lines = f.readlines()

# get the num of rows
num_lines = len(lines)

# create a GUI window
root = tk.Tk()
root.title("Update video URL")

# create label and text
label = tk.Label(root, text=f"There are {num_lines} lines of URL，please choose a integer from 1 to {num_lines} as Rec_id：")
label.pack()
entry = tk.Entry(root)
entry.pack()

def update_url():
    # get the entry by user
    rec_id = int(entry.get())

    # check if the entry between 1 to n
    if rec_id < 1 or rec_id > num_lines:
        result_label.config(text=f"Error：Please enter a integer from 1 to {num_lines}!")
    else:
        # choose the related row
        line = lines[rec_id - 1].strip()

        # connect to the database
        conn = pymysql.connect(host='localhost', user='root', password='1182372480', database='javawebdb')

        # get cursor
        cursor = conn.cursor()

        # update the data
        sql = "UPDATE recording SET videoURL = %s, user_id = %s, runtime = %s, status = 20, robot_id = %s WHERE Rec_id = %s"
        val = (line, read_file('D:\\pythonProject4\\video\\userid.txt')[rec_id - 1].strip(), read_file('D:\\pythonProject4\\video\\runtime.txt')[rec_id - 1].strip()[:10], read_file('D:\\pythonProject4\\video\\userid.txt')[rec_id - 1].strip(), rec_id)
        cursor.execute(sql, val)

        # submit the changing
        conn.commit()

        # close the cursor
        cursor.close()
        conn.close()

        result_label.config(text="videoURL has been updated！")

def read_file(file_path):
    with open(file_path, 'r') as f:
        return f.readlines()

# create the button and label
button = tk.Button(root, text="update", command=update_url)
button.pack()
result_label = tk.Label(root, text="")
result_label.pack()

# execute the GUI
root.mainloop()

