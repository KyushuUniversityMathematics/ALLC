# -*- coding: utf-8 -*-
from allc import *

'''
Webテスト　完全突破方法　2017年度版　p79 より引用

A~Eの五人が50m競争をし、その結果について次のように話した。ただし、
本当のことを言っているのは5位の人だけで、残りの4人は嘘を言っている。
同じ順位の人はいなかったとして、各メンバーの順位は何位か？

A 私はEより順位が下だった。
B Cが一位だった。
C 私は一位ではない
D Cが五位だった。
E 私はDより順位が下だった。
'''

prob = LpProblem('Race Problem', LpMinimize)


#A~Bは各メンバーの順位
A = Variable('A', 1, 5, LpInteger)
B = Variable('B', 1, 5, LpInteger)
C = Variable('C', 1, 5, LpInteger)
D = Variable('D', 1, 5, LpInteger)
E = Variable('E', 1, 5, LpInteger)

lg = LogicTranslator()

#A~Bの順位は違う。
member = [A, B, C, D, E]
for s in range(len(member)):
    for t in range(s+1, len(member)):
        lg += member[s] != member[t]

#各メンバーのコメント
lg += (A == 5) == (A > E)
lg += (B == 5) == (C == 1)
lg += (C == 5) == (C != 1)
lg += (D == 5) == (C == 5)
lg += (E == 5) == (E > D)

#目的関数の設定
prob += 0
#制約式の設定
for e in lg:
    prob += e

# 求解
prob.solve()

# 問題を解けたかどうかの結果
print("Status:", LpStatus[prob.status])

#結果の出力
for m in member:
    print('The order of {} is {}'.format(m.getName() ,int(m.value())))
          
