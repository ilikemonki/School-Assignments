# Meng Cha
# EE 381
# 3/28/19

import numpy as np
import matplotlib
import matplotlib.pyplot as plt

# (P1.1). a=1.0 ; b=4.0;
a = 1
b = 4
n = 10000
x = np.random.uniform(a, b, n)  # Generate n values of R.V. X

# Create bins and histogram
nbins = 30  # number of bars
bins = [float(x) for x in np.linspace(a, b, nbins + 1)]
print(bins)
h1, bin_edges = np.histogram(x, bins, density=True)
be1 = bin_edges[0:np.size(bin_edges) - 1]
be2 = bin_edges[1:np.size(bin_edges)]
b1 = (be1 + be2) / 2    # Get start and end of x-axis
print(b1)
barwidth = b1[1] - b1[0]
plt.close('all')
# Plot bar graph
fig1 = plt.figure(1)
plt.bar(b1, h1, width=barwidth, edgecolor='w')  # plot bar graph
plt.title("Uniform Random Variable")
plt.xlabel("n Values of R.V. X")
plt.ylabel("Probability of R.V. X")

# Get Probability Density Function for R.V. X
def UnifPDF(a, b, x):
    f = (1 / abs(b-a)) * np.ones(np.size(x))        # Get an array of the PDF for plotting.
    return f


f = UnifPDF(a, b, b1)
plt.plot(b1, f, 'r')    # plot PDF line

# Experimental Measurements
mu_x = np.mean(x)
sig_x = np.std(x)
print("Experimental Measurements:")
print("mu = " + str(mu_x))
print("sig = " + str(sig_x) + "\n")

# Theoretical Calculations
mu_x = (a + b) / 2
sig_x = (b - a) ** 2 / 12
print("Theoretical Calculations:")
print("mu = " + str(mu_x))
print("sig = " + str(sig_x) + "\n")

print("PDF: " + str(f[0]))
plt.show()
