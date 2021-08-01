# Meng Cha
# EE 381
# 3/13/2019


import numpy as np
import matplotlib
import matplotlib.pyplot as plt


# You have three identical multi-sided unfair dice.
# One roll is considered "success" if you get: “one” for the first die; “two”  for the second die;
# “three”  for the third die.
# You roll the three dice n=1000 times, and the number of successes in n rolls, will be your random variable "X".  Th
# Repeat N= 10,000 times
#  p=[0.2, 0.1, 0.15, 0.3, 0.2, 0.05]

# Returns one dice roll
def nSidedDie(p):
    diceLength = len(p)  # number of n-sided die
    r = np.random.random(1)  # randoms a roll
    probArray = np.cumsum(p)  # cumulative sum of array
    probArray = np.append(0.0, probArray)  # adds zero to beginning of array to compare later
    # Compare the dice roll to the dice probability
    for k in range(0, diceLength):  # Checks where the rolls fall into the probability
        if r > probArray[k] and r <= probArray[k + 1]:
            diceRoll = k + 1
            break  # breaks and moves to the next roll
    return diceRoll


p = [0.2, 0.1, 0.15, 0.3, 0.2, 0.05]  # Probabilities for each n-sided die
N = 10000      # Number of trials
n = 1000       # number of rolls
X = np.zeros(N, int)      # number of successes
for trials in range(0, N):      # Do the experiment N times
    for rolls in range(0, n):   # Roll the three dices n times
        myRoll = []
        for three in range(0, 3):   # Roll three dices
            myRoll.append(nSidedDie(p))     # append the dice face into an array
            if myRoll[0] != 1:      # If the first roll is not 1, stop the other two rolls.
                break
            if myRoll[0] == 1 and len(myRoll) > 1:      # Checks the second roll to see if it is 2
                if myRoll[1] != 2:  # if second roll is not 2, stop the last roll.
                    break
        if len(myRoll) == 3:    # if we have three rolls, check the third roll to see if it is 3.
            if myRoll[2] == 3:
                X[trials] += 1  # Increment number of success

# Stem Plot
plt.title("Experimental Bernoulli Trials")  # Stem plot title
plt.xlabel("Number of Successes")  # Stem plot x label
plt.ylabel("Probability of Successes")  # Stem plot y label
plt.hist(X, density=True)
plt.show()
