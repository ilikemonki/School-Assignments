

import numpy as np
import matplotlib
import matplotlib.pyplot as plt

n = 100
beta = 40
l = 1 / beta    # lambda

t = np.random.exponential(beta, n)  # Generate n values of R.V. T
t.sort()
y = l * np.exp(-l * t)      # Get PDF

# Plot Graph
plt.plot(t, y, 'r')  # plot PDF
plt.bar(t, y)   # plot bar
plt.title("Exponential Distribution")
plt.xlabel("n Values of R.V. T")
plt.ylabel("Probability of R.V. T")

# Experimental Measurements
mu_x = np.mean(t)
sig_x = np.std(t)
print("Experimental Measurements:")
print("mu = " + str(mu_x))
print("sig = " + str(sig_x) + "\n")

# Theoretical Calculations
mu_x = beta
sig_x = beta
print("Theoretical Calculations:")
print("mu = " + str(mu_x))
print("sig = " + str(sig_x) + "\n")
# Show graph
plt.show()
