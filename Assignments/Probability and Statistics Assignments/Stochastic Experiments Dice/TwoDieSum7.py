# Meng Cha
# EE 381 Probability and Stats Computing
# 1/29/2019

import random
import numpy as np
import matplotlib.pyplot as plt

# Roll a pair of fair dice and get their sum. Record the number of times it takes to get
# to sum 7. The first time you get 7 is a success.
# Repeat 100,000 times and keep track of the number of rolls to have success.
# Also stem plot the number of occurrence of the number of rolls it takes to sum 7.
# Also stem plot the probability of the dice sums

# Returns the amount of rolls until the sum is 7 and also returns an array of the sums
def twoDie():
    countRoll = 1  # Record the amount of rolls
    sumArr = []  # Array of the sums
    while 1:
        d1 = random.randrange(1, 7)  # Dice #1
        d2 = random.randrange(1, 7)  # Dice #2
        sumArr.append(d1 + d2)  # Append the sum of the dices to array
        if d1 + d2 == 7:    # if sum is, break and return, else increment and repeat
            break
        else:
            countRoll += 1
    return countRoll, sumArr


array = []  # Array for the countRoll return value
sumArray = np.zeros(13)  # array for dice sum 2 to 12
countArray = []  # Counts the "number of rolls"
xArray = []  # x-axis array for the number of rolls to sum 7
N = 100000
for x in range(0, N):
    cr, s = twoDie()  # set cr and s to the return values
    array.append(cr)  # append number of rolls into an array
    for y in range(0, len(s)):  # increment at the "sum of the roll" index
        sumArray[s[y]] += 1
array.sort()  # Sort the array to get the highest rolls value
for x in range(0, array[-1]):  # loops until the highest roll value
    if array.count(x) > 0:
        xArray.append(x)    # create the x-axis array
        countArray.append(array.count(x))   # count the amount of specific roll and record into array

# Stem Plot for the occurrence of rolls until sum 7
fig1 = plt.figure(1)
plt.title("Sum of Two Die")
plt.xlabel("Number of Rolls to Sum 7")
plt.ylabel("Number of Occurrence")
plt.stem(xArray, countArray)

# Stem Plot for the probability of the sum of two dice
fig2 = plt.figure(2)
plt.title("Sum of Two Die: Probability")
plt.xlabel("Sum of two Dice")
plt.ylabel("Probability")
plt.stem(sumArray)
plt.show()
