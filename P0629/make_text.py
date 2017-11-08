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
n=20
aaa=0
x=[[0 for i in range(n)] for j in range(n)]
for s in ss:
    if s[0]!='x' :
	continue
    t1=s.find("[")
    t2=s.find("]")
    i=int(s[t1+1:t2])
    s=s[t2+1:]
    t1=s.find("[")
    t2=s.find("]")
    j=int(s[t1+1:t2])
    s=s[t2+1:]
    k=int(s[-1])
    x[i][j]=k
    if i>aaa :
	aaa=i

n=aaa-1
for i in range(1,n+1):
    for j in range(1,n+1):
	print("x["+str(i)+"]["+str(j)+"]="+str(x[i][j]))

nf=open("sample.txt","w")
for i in range(1,n+1):
    for j in range(1,n+1):
	nf.write(str(x[i][j])+' ')
    nf.write("\n")
nf.close()
