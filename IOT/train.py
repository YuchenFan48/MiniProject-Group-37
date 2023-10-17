from ultralytics import YOLO

# Load a model
model = YOLO("D:\\pythonProject4\\data\\model1.pt")  # load a pretrained model (recommended for training)

# Train the model with 2 GPUs
if __name__=='__main__':
    model.train(data='D:\\pythonProject4\\data\\mini.yaml', epochs=300, imgsz=640, device=0,workers=4)