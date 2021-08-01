# Meng Cha
# EE 381 Probability and Stats Computing
# 1/30/2019

import random
import numpy as np

# Toss 100 fair coins and record the number of heads.
# If you get exactly 50 heads, it is a success
# Repeat 100,000 times and count the total successes and calculate the probability of success.


# Toss coin 100 times and returns 1 if we get 50 heads; returns 0 by default
def tossingCoin():
    r = np.random.randint(0, 2, 100)    # Randomizes either 0 or 1 and puts them in array
    sum = r.sum()   # add the array
    if sum == 50:   # return 1 if we get 50 heads.
        return 1
    return 0


successCount = 0    # record the success of the experiments
N = 100000
for x in range(0, N):
    successCount += tossingCoin()   # add all the return values
print(str(successCount) + " out of " + str(N))
prob = successCount / N * 100   # get probability
print(str(prob) + "%")
