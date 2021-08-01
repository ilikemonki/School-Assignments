# Meng Cha
# EE 381
# 5/8/2019

import numpy as np
import matplotlib
import matplotlib.pyplot as plt

# Markov Chain
N = 10000   # number of experiments
n = 15      # time steps
pRNS = np.zeros((n, 3))     # plot points for R, N, S
state = [0 for i in range(0, n)]
X = np.chararray((n, N))
X[:] = 0

# Use dictionary for prob and dist b/c it is easier to remember what is what
# Probability
prob = {'p11': 1/2, 'p12': 1/4, 'p13': 1/4, 'p21': 1/4, 'p22': 1/8,
        'p23': 5/8, 'p31': 1/3, 'p32': 2/3, 'p33': 0}
# Distribution Vector
dist = {'d1': 1/4, 'd2': 0, 'd3': 3/4}

# Get random prob and check which distribution vector it is in.
for i in range(0, N):
    rand = np.random.random()
    if rand <= dist['d1']:
        s0 = 'R'
    elif dist['d1'] < rand <= dist['d1'] + dist['d2']:
        s0 = 'N'
    elif rand > dist['d1']+dist['d2']:
        s0 = 'S'
    state[0] = s0

    # The state transitions
    for k in range(0, n - 1):
        s = state[k]
        r = np.random.random()
        if s == 'R':
            if r <= prob['p11']:
                state[k + 1] = 'R'
            elif prob['p11'] < r <= prob['p11'] + prob['p12']:
                state[k + 1] = 'N'
            elif r > prob['p11']+prob['p12']:
                state[k + 1] = 'S'
        elif s == 'N':
            if r <= prob['p21']:
                state[k + 1] = 'R'
            elif prob['p21'] < r <= prob['p21'] + prob['p22']:
                state[k + 1] = 'N'
            elif r > prob['p21'] + prob['p22']:
                state[k + 1] = 'S'
        elif s == 'S':
            if r <= prob['p31']:
                state[k + 1] = 'R'
            elif prob['p31'] <= r <= prob['p31'] + prob['p32']:
                state[k + 1] = 'N'
            elif r > prob['p31'] + prob['p32']:
                state[k + 1] = 'S'
    X[:, i] = state

# Count RNS
for j in range(0, n):
    rns = [0, 0, 0]
    x = X[j, :]
    for m in range(N):
        c = str(x[m], 'utf-8')  # convert x from binary back to char
        if c == 'R':
            rns[0] += 1
        elif c == 'N':
            rns[1] += 1
        elif c == 'S':
            rns[2] += 1
    pRNS[j, :] = [rns[0]/N, rns[1]/N, rns[2]/N] # Get prob

# Plot Graph
# Simulation Three state
nv = np.linspace(0, n, num=n)

plt.figure(1)
plt.plot(nv, pRNS[:, 0], marker='o', ls='--')
plt.plot(nv, pRNS[:, 1], marker='o', ls='--')
plt.plot(nv, pRNS[:, 2], marker='o', ls='--')
plt.legend(('R', 'N', 'S'))
plt.title('Simulation Three-state Markov Chain')
plt.xlabel('Step Number')
plt.ylabel('Probability')

# Calculated Three state
pRNS2 = np.zeros((n, 3))
Pm = np.matrix([[prob['p11'], prob['p12'], prob['p13']], [prob['p21'], prob['p22'], prob['p23']],
                [prob['p31'], prob['p32'], prob['p33']]])
dm = np.matrix([dist['d1'], dist['d2'], dist['d3']])
pRNS2[0, :] = dm

for k in range(0, n-1):
    pRNS2[k + 1, :] = np.matmul(pRNS2[k, :], Pm)

plt.figure(2)
plt.plot(nv, pRNS2[:, 0], marker='o', ls='--')
plt.plot(nv, pRNS2[:, 1], marker='o', ls='--')
plt.plot(nv, pRNS2[:, 2], marker='o', ls='--')
plt.legend(('R', 'N', 'S'))
plt.title('Calculated Three-state Markov Chain')
plt.xlabel('Step Number')
plt.ylabel('Probability')

# Show graph
plt.show()

