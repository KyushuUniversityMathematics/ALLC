# -*- coding: utf-8 -*-
from allc import *

#問題の作成
prob = LpProblem('Number link', LpMinimize)

#行数、列数、数字の個数と各種集合の設定
n, m, t = 7, 7, 5
I, J, K = range(1,n+1), range(1,m+1), range(t+1)
D = ((1,0),(0,1),(-1,0),(0,-1))

#初期値。便宜上周りを0で埋めておく。
N =  [[0,0,0,0,0,0,0,0,0],
      [0,1,0,0,0,0,0,0,0],
      [0,0,0,0,0,0,2,0,0],
      [0,0,0,0,0,0,0,0,0],
      [0,0,0,4,0,0,5,0,0],
      [0,0,0,0,0,0,0,0,0],
      [0,0,3,4,0,0,0,0,0],
      [0,0,0,0,2,3,1,5,0],
      [0,0,0,0,0,0,0,0,0]]

#範囲0からtの整数変数x[i][j]の作成
x = Variable.dicts('x',(range(n+2),range(m+2)), 0, t, LpInteger)
#目的関数の値
ob = Variable('ob', cat=LpInteger)

#論理式をMILP制約に変換するクラス
lg = LogicTranslator()

#周りのマスは0
for i in range(n+2):
    lg += x[i][0] == 0
    lg += x[i][m+1] == 0
for j in range(m+2):
    lg += x[0][j] == 0
    lg += x[n+1][j] == 0

#初期値
lg += forall(I, lambda i:
        forall(J, lambda j:
            (N[i][j]>=1) >> (x[i][j]==N[i][j]) ))

#初期値が与えられているマスに対して、そのマスと
#同じ数字が上下左右でちょうど一つのみ隣接する。
lg += forall(I, lambda i:
        forall(J, lambda j:
            (N[i][j]>=1) >> exists(1)(D, lambda d:
                N[i][j]==x[i+d[0]][j+d[1]]) ))

#初期値が与えられていないマスに対して、そのマスと
#同じ数字が上下左右でちょうど二つのみ隣接する。
lg += forall(I, lambda i:
        forall(J, lambda j:
            ((N[i][j]==0) & (x[i][j]>=1)) >>
            exists(2)(D, lambda d:
                x[i][j]==x[i+d[0]][j+d[1]]) ))

#目的関数の定義
lg += ob == sum_(x[i][j]>=1 for i in I for j in J)

#問題に目的値を設定
prob += ob
#制約式の設定
for e in lg:
    prob += e

#問題を解く
prob.solve()

#結果の出力
for i in I:
    for j in J:
        print('{} '.format(int(x[i][j].value())),end='')
    print()
