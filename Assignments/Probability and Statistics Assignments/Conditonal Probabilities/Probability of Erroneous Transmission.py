# Meng Cha
# EE 381
# 2/27/2019

import random
import numpy as np

# Create a message transmission through a noisy communication channel.
# Transmit a 1-bit message S and look at the received signal R.
# If R = S, it is successful, else failure. Repeat N = 100,000 times.
# Find Probability of failures.

# Given variables
p0 = 0.6
e0 = 0.05
e1 = 0.03

N = 100000      # Number of experiments
success = 0     # Counter of successes
S = np.zeros(N, int)    # S array to store signal s
R = np.zeros(N, int)    # R array to store signal r
for k in range(0, N):
    m = np.random.rand()    # randomizes message m
    s = 1       # initialize and declare signal s

    if m <= p0: # set s to zero if m <= p0.
        s = 0
    S[k] = s    # store signal s in array

    t = np.random.rand()    # randomizes t
    r = 1       # initialize and declare signal r
    if (s == 0 and t > e0) or (s == 1 and t <= e1):     # Set r to zero if s and t meet the conditions
        r = 0
    R[k] = r    # store signal r in array

    if s == r:  # Check if r equals s
        success += 1    # Increment our success counter

print("Number of successes: " + str(success))
failures = N - success  # Get number of failures
print("Number of failures: " + str(failures))
probFails = failures / N    # Get the probability of failures
print("Probability of failures: " + str(probFails))
print(str(probFails * 100) + "%")   # Convert probability to percentage

