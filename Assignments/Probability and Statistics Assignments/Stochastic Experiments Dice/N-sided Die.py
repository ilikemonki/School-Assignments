# Meng Cha
# EE 381 Probability and Stats Computing
# 1/28/2019

import numpy as np
import matplotlib
import matplotlib.pyplot as plt

# Write a function that creates a random number with a single roll
# using the probability vector p as parameter. Output the number of the die face.
# Roll the die 10,000 times and plot in Stem plot.


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


p = [0.10,  0.15,  0.20,  0.05,  0.30, 0.10, 0.10]  # Probabilities for each n-sided die
count = np.zeros(len(p))  # Array to record the dice face
for x in range(0, 10000):  # Loop 10,000 times
    r = nSidedDie(p)  # Call function and returns a dice face
    count[r - 1] += 1  # Record dice face of array
print(count)
# Stem Plot
plt.title("n-SidedDie Outcomes")  # Stem plot title
plt.xlabel("Dice Face")  # Stem plot x label
plt.ylabel("Number of Dice Rolls")  # Stem plot y label
plt.stem(range(1, len(p) + 1), count)
plt.show()
