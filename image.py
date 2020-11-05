#Image processing in python

import cv2

def display_original_image():
	cv2.imshow('Original', img)
	wait_and_destroy()

def wait_and_destroy():
	cv2.waitKey(0)
	cv2.destroyAllWindows()	

def display_gray_scale_image():
	gray_colour_image = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
	cv2.imshow('Grayscale', gray_colour_image)
	wait_and_destroy()

def rotate_image():
	print("1. Rotate 90 degrees\n2. Rotate 180 degrees\n3. Rotate 270 degrees")
	rotate_option = int(input("Choose your option: "))
	if rotate_option == 1:
		get_image_angle(90)
	elif rotate_option == 2:
		get_image_angle(180)
	elif rotate_option == 3:
		get_image_angle(270)
	else:
		print("Invalid.")

def get_image_angle(angle):
	(height, width) = img.shape[:2]
	center = (width / 2, height / 2)
	scale = 1.0
	image_matrix = cv2.getRotationMatrix2D(center, angle, scale)
	rotated90 = cv2.warpAffine(img, image_matrix, (height, width))
	cv2.imshow('Image rotated by ' + str(angle) + ' degrees',rotated90)
	wait_and_destroy()

def crop_image():
	(height, width) = img.shape[:2]
	start_row, start_col = int(height * 0.15), int(width * 0.15)
	end_row, end_col = int(height * 0.85), int(width * 0.85)
	cropped_image = img[start_row:end_row, start_col:end_col]
	cv2.imshow("Cropped Image", cropped_image)
	wait_and_destroy()

img = cv2.imread('C:/Users/HOME/Desktop/a.jpg')
functions = [display_original_image, display_gray_scale_image, rotate_image, crop_image]
while True:
	print("1. Original image\n2. Gray scale image\n3. Rotate image\n4. Crop image\n5. Exit")
	user_option = int(input("Choose your option: "))
	if user_option == 5:
		print("Are you sure? ")
		print("1. Exit\n2. Cancel")
		exit_option = int(input("Choose your option: "))
		if exit_option == 1:
			exit()
	elif user_option > 0 and user_option < 5:
		functions[user_option - 1]()
	else:
		print("Invalid.")