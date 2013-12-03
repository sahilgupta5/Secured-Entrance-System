#!/usr/bin/python

import serial
import MySQLdb

#establish connection to MySQL. You'll have to change this for your database.
dbConn = MySQLdb.connect("localhost","root","ece4894project3#","ece4894project") or die ("could not connect to database")
#open a cursor to the database
cursor = dbConn.cursor()

device = '/dev/tty.usbmodem1421' #this will have to be changed to the serial port you are using
try:
  print "Trying...",device 
  arduino = serial.Serial(device, 9600) 
except: 
  print "Failed to connect on",device    
 
try: 
  data = arduino.readline()  #read the data from the arduino
  print "hello"
  #Here we are going to insert the data into the Database
  try:
    cursor.execute("INSERT INTO Temporary (TempCol) VALUES (%s)", data)
    dbConn.commit() #commit the insert
    cursor.close()  #close the cursor
  except MySQLdb.IntegrityError:
    print "failed to insert data"
  finally:
    cursor.close()  #close just incase it failed
except:
  print "Failed to get data from Arduino!"
