import obd
import os
import time
from datetime import datetime


#Copy logs to Nifi directory  
def copyFiles(filename):
  try:
    os.popen('cp "%s" /home/pi/log4nifi' % (filename))
    print("File %s copied " % (filename))
  except:
    print("An exception occurred")

#Create txt file     
def create_file(line): 
    filename  = datetime.now()
    with open(filename.strftime("%b-%d-%Y_%H%M-%s")+".txt", "w") as file: 
        file.write(line)
    copyFiles(filename.strftime("%b-%d-%Y_%H%M-%s")+".txt")   

# OBD get data 
def getData():
  
  speedCmd = connection.query(obd.commands.SPEED,force=True)
  speedVal = str(speedCmd.value)
  rpmCmd = connection.query(obd.commands.RPM,force=True)
  rpmVal = str(rpmCmd.value)
  print("Speed: " + speedVal + ", Rpm: " + rpmVal)
  text= "Speed: " + speedVal + ", Rpm: " + rpmVal
  create_file(text)


  
if __name__ == "__main__":
# OBD setup
  obd.logger.setLevel(obd.logging.DEBUG)

# Connect to OBDII adapter
  ports = obd.scan_serial()
  print("Ports: ")
  print(ports)

  connection = obd.OBD(ports[0])
  print("Connection status: ")
  print(connection.status())

# Print supported commands
  commands = connection.supported_commands
  print("Supported commands: ")
  for command in commands:
    print(command.name)
  
# Get OBD data, speed and RPM
  while True:
    getData()
    time.sleep(10)
