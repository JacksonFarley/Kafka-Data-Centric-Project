import sys
import threading
import subprocess
import time
from subprocess import PIPE

n = 0

#lock = threading.RLock()

def my_counter(my_input):
    while(True):
        print(my_input)
        time.sleep(0.1)
#    for line in my_input: 
        n = n+1
        print('see a line')
        #if(n == 1000):
        #    break
        global stop_flag

def consume(proc):
    my_stdout = proc.communicate()

def my_timer(proc):
    a = 1
    while(1):
        time.sleep(0.1)
        print('{} s'.format(0.1*a))
        a = a+1
        if (a == 20):
            proc.kill()
    



#c = threading.Thread(name='counter',target=my_counter, args=(sys.stdin))
#t.setDaemon(True)

stop_flag = False


cmd ="bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic connect-test --from-beginning" 
proc = subprocess.Popen(cmd,shell=True,stdout=PIPE)
#c.start()

con = threading.Thread(name='main',target=consume,args=proc)

t = threading.Thread(name='timer',target=my_timer,args=proc)

con.start()
t.start()




#cmd ="bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic connect-test --from-beginning" 
#proc = subprocess.Popen(cmd,shell=True,stdout=PIPE)
#proc.communicate()
    

print("communicating")
#time.sleep(2)

#stop_flag = True
#c.join()

print(n) 
exit(0)



