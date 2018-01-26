#-*-coding:utf-8-*-
'''
ジュニア数オリ2014予選第二問参照
500以下の正の整数nについてA,B,C,Dの四人が以下のように
話している。
A「nは2でちょうど3回だけ割り切れる」
B「nは3でちょうど2回だけ割り切れる」
C「nは7でちょうど1回だけ割り切れる」
D「nの各桁の数字の和は15だ」
このうち、3人の発言は正しいが、残りの一人の発言は誤りである。
このとき、nを求めよ。
************************
方針
1,10,100の位の数字をe1,e2,e3とする。
'''
from allc import *

problem = LpProblem('jjmo_2014', LpMinimize)

member = ['A', 'B', 'C', 'D']

comments = Variable.dicts('Comment', (member,), cat=LpBinary) 

# e1, e2, e3 はそれぞれnの各桁の数字を表すとする。
e1 = Variable('e1', 0, 9, cat=LpInteger)
e2 = Variable('e2', 0, 9, cat=LpInteger)
e3 = Variable('e3', 0, 9, cat=LpInteger)
n = Variable('n', 0, 500, cat=LpInteger)

problem += 0

lg = LogicTranslator()

# e1,e2,e3はnの各桁の数字
lg += n == e1 + 10*e2 + 100*e3

# A,B,C,Dのコメントの内容
lg += comments['A'] == ((n % (2**3) == 0) & (n % (2**4) != 0))
lg += comments['B'] == ((n % (3**2) == 0) & (n % (3**3) != 0))
lg += comments['C'] == ((n % (7**1) == 0) & (n % (7**2) != 0))
lg += comments['D'] == (e1 + e2 + e3 == 15)

# 四人の中でちょうど三人の発言が正しい
lg += exists(3)(member, lambda person:
        comments[person])


for e in lg:
    problem += e

status = problem.solve()
print(LpStatus[status])

for key,value in comments.items():
    print(key, bool(value.value()))
for e in [e1, e2, e3]:
    print(e.getName(), int(e.value()))
print(n.getName(), int(n.value()))
