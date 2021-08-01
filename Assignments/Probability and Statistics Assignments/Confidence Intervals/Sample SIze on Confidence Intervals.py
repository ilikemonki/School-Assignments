# Meng Cha
# EE 381
# 4/22/19

import numpy as np
import matplotlib
import matplotlib.pyplot as plt
import random

# Total number of bearings: N = 1, 500, 000
# Population mean: μ = 55 grams
# Population standard deviation: σ = 5 grams
# Sample sizes: n = 1, 2, ... 200

# Part 1
N = 1500000 # Population size
mu = 55     # mean weight
sigma = 5   # Standard Deviation
n = list(range(201))   # sample size
n.pop(0)    # Take out the zero from n list
B = np.random.normal(mu, sigma, N)  # create Normal Distribution variables
xbar = []   # sample mean
S = []  # positive S.D. for 95% confidence
S2 = [] # neg S.D.
S3 = [] # pos S.D. for 99% confidence
S4 = [] # neg S.D.

for z in n:     # loop 200 times
    X = B[random.sample(range(N), z)]   # Get random z-values from B
    xbar.append(np.mean(X))     # get the mean
    # Get S.D. of 95% confidence
    S.append(mu + 1.96 * (sigma / np.sqrt(z)))
    S2.append(mu - 1.96 * (sigma / np.sqrt(z)))
    # Get S.D. of 99% confidence
    S3.append(mu + 2.58 * (sigma / np.sqrt(z)))
    S4.append(mu - 2.58 * (sigma / np.sqrt(z)))
m = (np.mean(xbar))     # Get the mean of the mean to plot a horizontal line

# Plot 95% confidence interval
plt.figure("Figure 1")
plt.hlines(m, 0, 200)
plt.plot(n, S, ls='--')
plt.plot(n, S2, ls='--')
plt.plot(n, xbar, marker="x", ls='none')  # plot
plt.title("Sample Means and 95% Confidence Intervals")
plt.xlabel("Sample Size")
plt.ylabel("x_bar")

# Plot 99% confidence interval
plt.figure("Figure 2")
plt.hlines(m, 0, 200)
plt.plot(n, S3, ls='--')
plt.plot(n, S4, ls='--')
plt.plot(n, xbar, marker="x", ls='none')  # plot
plt.title("Sample Means and 99% Confidence Intervals")
plt.xlabel("Sample Size")
plt.ylabel("x_bar")

# Part 2
# Using the sample mean to estimate population mean
samplesize = [5, 40, 120]   # n number of sample size
M = 10000   # number of trials

t95 = [2.78, 2.02, 1.98]    # t-table values for 95% confidence on n = 5, 30, and 120
print("95% Confidence:")
for ss in range(0, len(samplesize)):
    countNormal = 0  # count Normal Distribution successes
    countT = 0  # count student's t-Distribution successes
    for m in range(0, M):
        X = B[random.sample(range(N), samplesize[ss])]  # Get random samplesize-values from B
        x_bar = np.mean(X)  # get mean
        SD = np.std(X)  # sample S.D.
        # Get mu Lower and Upper for Normal Dist
        muL = x_bar - 1.96 * (SD / np.sqrt(samplesize[ss]))
        muU = x_bar + 1.96 * (SD / np.sqrt(samplesize[ss]))
        if muL <= mu <= muU:    # if actual mu is included in the intervals then, increment
            countNormal += 1
        # Get mu Lower and Upper for t-dist
        muL = x_bar - t95[ss] * (SD / np.sqrt(samplesize[ss]))
        muU = x_bar + t95[ss] * (SD / np.sqrt(samplesize[ss]))
        if muL <= mu <= muU:
            countT += 1
    print("n =", samplesize[ss])
    print("Normal Success:", countNormal, countNormal / M)
    print("t Success:", countT, countT / M, "\n")

# Repeat for 99 confidence
t99 = [4.60, 2.71, 2.62]    # t-table values for 99% confidence on n = 5, 30, and 120
print("99% Confidence:")
for ss in range(0, len(samplesize)):
    countNormal = 0  # count Normal Distribution successes
    countT = 0  # count student's t-Distribution successes
    for m in range(0, M):
        X = B[random.sample(range(N), samplesize[ss])]
        x_bar = np.mean(X)
        SD = np.std(X)  # sample S.D.
        # Get mu Lower and Upper for Normal Dist
        muL = x_bar - 2.58 * (SD / np.sqrt(samplesize[ss]))
        muU = x_bar + 2.58 * (SD / np.sqrt(samplesize[ss]))
        if muL <= mu <= muU:
            countNormal += 1
        # Get mu Lower and Upper for t-Dist
        muL = x_bar - t99[ss] * (SD / np.sqrt(samplesize[ss]))
        muU = x_bar + t99[ss] * (SD / np.sqrt(samplesize[ss]))
        if muL <= mu <= muU:
            countT += 1
    print("n =", samplesize[ss])
    print("Normal Success:", countNormal, countNormal / M)
    print("t Success:", countT, countT / M, "\n")

plt.show()  # Show graph
