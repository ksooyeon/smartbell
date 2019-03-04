from picamera import PiCamera
from time import sleep
import RPi.GPIO as GPIO
import time
import pyrebase
import json
import os

config = {
		"apiKey" : "AIzaSyByRfVjkbM-lgjcwmOlC07IF7kE6gr_Zbg",
		"authDomain" : "knock-7b4fc.firebaseapp.com",
		"databaseURL" : "https://knock-7b4fc.firebaseio.com/",
		"storageBucket" : "knock-7b4fc.appspot.com"
}//firebase 계정 연동

firebase = pyrebase.initialize_app(config)


GPIO.setmode(GPIO.BCM)
GPIO.setup(21,GPIO.IN)  //인체감지센서 연결

camera = PiCamera()

while True:
	input_state = GPIO.input(21)
	if input_state==True:
		camera.start_preview()
		sleep(3)
		camera.capture('/home/pi/Desktop/image.jpg')
		camera.stop_preview()
		time.sleep(20)
		//센서 인식되면 카메라 촬영

		uploadfile = "/home/pi/Desktop/image.jpg"
		s = os.path.splitext(uploadfile)[1]
		now = 'recent'
		filename = now + s

		storage = firebase.storage()
		storage.child(filename).put(uploadfile)
		//촬영 된 사진 firebase에 업로드