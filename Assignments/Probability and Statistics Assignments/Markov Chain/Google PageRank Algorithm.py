# Meng Cha
# EE 381
# 5/8/2019

import numpy as np
import matplotlib
import matplotlib.pyplot as plt


n = 20  # number of steps
# Probability
prob = np.matrix([[0, 1, 0, 0, 0], [1/2, 0, 1/2, 0, 0], [1/3, 1/3, 0, 0, 1/3],
               [1, 0, 0, 0, 0], [0, 1/3, 1/3, 1/3, 0]])
# vector
vector = np.matrix([[1/5, 1/5, 1/5, 1/5, 1/5],
               [0, 0, 0, 0, 1]])
# vector name
v = ['v1', 'v2']
for i in range(0, 2):
    s = vector[i, :]
    Y = np.zeros((n, 5))
    Y[0, :] = s

    for j in range(0, n - 1):
        Y[j+1, :] = np.matmul(Y[j, :], prob)

    # plot graph
    nv = np.linspace(0, n, num=n)
    plt.figure(i + 1)
    plt.plot(nv, Y, marker='o', ls='--')
    plt.legend(('A', 'B', 'C', 'D', 'E'))
    plt.title('Google PageRank Algorithm: ' + v[i])
    plt.xlabel('Time step')
    plt.ylabel('Probability')

# Show plot
plt.show()
