# Meng Cha
# EE 381
# 3/28/19

import numpy as np
import matplotlib
import matplotlib.pyplot as plt

# (P1.3). mu=2.5; sigma= 0.75

n = 100
mu = 2.5
sigma = 0.75

x = np.random.normal(mu, sigma, n)  # Generate n values of R.V. X
x.sort()
y = (1 / (sigma * np.sqrt(2 * np.pi))) * np.exp(-((x - mu) ** 2) / (2 * sigma ** 2))      # Get PDF

# Plot Graph
plt.plot(x, y, 'r')  # plot PDF
plt.bar(x, y, width=0.05, edgecolor='w')   # plot bar
plt.title("Normal Random Variable")
plt.xlabel("n Values of R.V. X")
plt.ylabel("Probability of R.V. X")

# Experimental Measurements
mu_x = np.mean(x)
sig_x = np.std(x)
print("Experimental Measurements:")
print("mu = " + str(mu_x))
print("sig = " + str(sig_x) + "\n")

# Theoretical Calculations
mu_x = mu
sig_x = sigma
print("Theoretical Calculations:")
print("mu = " + str(mu_x))
print("sig = " + str(sig_x) + "\n")
# Show graph
plt.show()
