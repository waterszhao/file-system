import random
import matplotlib.pyplot as plt
import time
import numpy as np

def bubbleSort(A):
    for i in range(1,len(A)-1):
        for j in range(len(A)-i):
            temp = A[j]
            if A[j] > A[j + 1]:
                A[j] = A[j+1]
                A[j+1] = temp
    return A

def merge_sort(A):
    if len(A) <= 1:
        return A
    else:
        middle = len(A) // 2
        LA = A[:middle]
        RA = A[middle:]
        LA = merge_sort(LA)
        RA = merge_sort(RA)
        SA = merge(LA,RA)
        return SA

def merge(LA,RA):
    SA = []
    l = 0
    r = 0
        
    while l < len(LA) and r < len(RA):
        if LA[l] <= RA[r]:
            SA.append(LA[l])
            l += 1
        elif LA[l] >= RA[r]:
            SA.append(RA[r])
            r += 1
    SA += LA[l:]
    SA += RA[r:]
    return SA

def createRandomArray(numOfArray,numRange):
    arr = []
    for i in range(0,numOfArray):
        arr.append(random.randint(0, numRange))
    return arr

PowerArray = []
Time = []

power = 500 
step = power
amount = 40

while power <= step * amount:
    PowerArray.append(power)
    TimeTemp1 = [] 
    TimeTemp2 = [] 
    fmerge = np.log(power) * power - power + 1
    fbubble =  power * (power - 1) /2 
    for j in range(0,50):
        
        oldT = time.time()
        array = createRandomArray(power, power)
        newT = time.time()
        
        oldtime = time.time()
        merge_sort(array)
        newtime = time.time()
            
        TimeTemp1.append(newtime-oldtime) 
        TimeTemp2.append(newT-oldT) 
        
    Time.append(np.average(TimeTemp1) / fmerge) 
    rate = np.average(TimeTemp2) / np.average(TimeTemp1) + np.average(TimeTemp2)
    
    print('array size: {} initialization time: {}%'.format(power,rate * 100 ))
    power += step 

plt.xlabel("array size")
plt.ylabel("c")
plt.plot(PowerArray, Time) 
plt.show()