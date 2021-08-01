# Meng Cha
# EE 381 Probability and Stats Computing
# 2/8/2019

import random
import numpy as np

# A computer system uses a 4-letter password for login. A hacker creates a list of 'm' random 4-letter words
# to match the password. Note that some may be duplicates. 'm' is given.
# You have your password and are going to check the hacker's list if it matches.
# If there is a match, it is a success.
# Repeat N = 1,000 times and find the probability that at least one of the words matches.
# The hacker creates a longer list of k * m. 'k' and 'm' is given. Do the experiment like the steps above.
# Then find 'm' so that the probability is approximately equal to 0.5.
# INSTEAD OF LETTERS, WE CONVERT THEM INTO NUMBERS. "aaaa" = 0, "aaab" = 1, "zzzz" = 456,975

m = 45000   # m = 80,000;
N = 1000    # number of times we call the function.
k = 7      # k = 7 is given to create a longer list. Change k to 1 when we don't need the longer list.
mk = m * k  # multiplies m and k.


# Compares the password to randomly created passwords and returns a counter.
def PWHacking(myPW):
    count = 0   # Count successful passwords
    for z in range(0, N):   # Max number of experiments
        r = np.random.randint(0, 456976, mk)  # create an array with 'mk' amount of values.
        compare = np.count_nonzero(r == myPW)   # checks r array to see if the password matches any of them.
        if compare >= 1:    # If there is a match, we increment the counter.
            count += 1
    return count


# the range is from 0 to 456976 b/c the possibility of is 26^4.
# The 4-letter word "aaaa" = 0, "aaab" = 1, "zzzz" = 456,975
pw = random.randint(0, 456976)    # Randomly creates our password
c = PWHacking(pw)   # call the function and use the password as the parameter
print("m = " + str(m))
print("k = " + str(k))
print("m * k = " + str(mk))
print("Number of matches out of " + str(N) + ": " + str(c))
print("Probability: " + str(c / N))
