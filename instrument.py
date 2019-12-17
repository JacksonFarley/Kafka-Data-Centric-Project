
import subprocess, threading
from subprocess import Popen, PIPE;
from threading import Timer



class Command(object):
    def __init__(self, cmd):
        self.cmd = cmd
        self.process = None
        self.out = []

    def run(self, timeout):
        def target():
            print 'Thread started'
            self.process = subprocess.Popen(self.cmd, shell=True)
            (self.out, err) = self.process.communicate()
            print 'Thread finished'

        
        
        thread = threading.Thread(target=target)
        thread.start()
       
        for x in range(20):
            thread.join(.1)
            if thread.is_alive():
                print("we tried")
#               print(len(self.out))

        thread.join(timeout - 2)
        if thread.is_alive():
            print('Terminating process')
            self.process.kill()
            thread.join()
        print(self.process.returncode)

#subprocess.check_output(["bin/kafka-console-consumer.sh"," --bootstrap-server localhost:9092", "--topic=connect-test","--from-beginning"])
#retcode = subprocess.call("bin/kafka-console-consumer.sh" + " --bootstrap-server localhost:9092 --topic connect-test --from-beginning", shell=True)

cmd ="bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic connect-test --from-beginning"
com = Command(cmd)
com.run(timeout = 5)
#process =  Popen(cmd, shell=True, stdin=subprocess.PIPE, stdout=subprocess.PIPE, close_fds = True)
#(child_stdout, child_stdin) = (process.stdout, process.stdin)
#output = process.communicate()





#subprocess.run(cmd,shell=True,check=True)
