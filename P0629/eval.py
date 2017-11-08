import gurobipy
import sys
import os

args=sys.argv

os.system("java Test "+args[1]+" > test1.lp")
m=gurobipy.read("test1.lp")
m.optimize()
m.write("test1.sol")

ss=[]
for line in open("test1.sol","r"):
    if line[0] !="@" and line !="":
        ss.append(line[:-1])

ss.sort()
for s in ss:
    print(s)
