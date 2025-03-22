import os
pid = os.fork()

if pid > 0:
    print("Parent waiting...")
    os.wait()
    print("Parent resumes after child.")
elif pid == 0:
    print("Child process executing...")
