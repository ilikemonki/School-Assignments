# Meng Cha
# EE 381
# 3/28/19

import numpy as np
import matplotlib
import matplotlib.pyplot as plt

# Beta = 40 days
beta = 40
n = 24
N = 100
y = []
for k in range(0, N):
    t = np.random.exponential(beta, n)  # Generate n values of R.V. T
    C = t.sum()

    y.append((1 / beta) * np.exp((-1 / beta) * C))

# Plot Graph
plt.plot(t, y, 'r')  # plot PDF
plt.bar(t, y)   # plot bar
plt.title("Exponential Distribution")
plt.xlabel("n Values of R.V. T")
plt.ylabel("Probability of R.V. T")
# Show graph
plt.show()
