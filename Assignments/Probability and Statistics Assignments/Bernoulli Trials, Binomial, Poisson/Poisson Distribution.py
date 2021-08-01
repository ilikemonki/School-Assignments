# Meng Cha
# EE 381
# 3/13/2019


import numpy as np
import matplotlib
import matplotlib.pyplot as plt
import math

# Use Poisson Distribution to calculate the probability 'p' of success in a single roll of the three dice.

p = [0.2, 0.1, 0.15, 0.3, 0.2, 0.05]  # Probabilities for each n-sided die
pProb = p[0] * p[1] * p[2]
n = 1000       # number of rolls
k = 20  # number of successes
X = np.zeros(k)      # Prob of successes
Lambda = n * pProb

for x in range(1, k):
    r = x - 1
    # Poisson Distribution
    X[x] = (Lambda ** r) * math.exp(-Lambda) / math.factorial(r)

# Stem Plot
bins = []
for i in range(0, k):
    bins.append(i)
plt.bar(bins, X)
plt.title("Bernoulli Trials: PMF – Poisson Approximation")  # Stem plot title
plt.xlabel("Number of Successes in n trials")  # Stem plot x label
plt.ylabel("Probability")  # Stem plot y label
plt.show()
