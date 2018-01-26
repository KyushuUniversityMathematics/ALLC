# -*- coding: utf-8 -*-
from allc import *

#初期値
N0 = [[0,0,7,0,0,0,2,0,0],
      [0,0,0,0,0,4,0,0,0],
      [4,0,0,0,0,2,0,0,8],
      [0,0,0,0,0,1,0,0,5],
      [2,0,0,0,5,0,0,1,0],
      [0,0,6,0,8,0,0,7,0],
      [0,8,0,0,0,0,0,0,0],
      [1,0,0,5,9,0,8,0,3],
      [7,2,0,4,0,0,0,0,0]]

#添字を揃えるために添字を一つずらす。
N = [[0 for j in range(10)] for i in range(10)]
for i in range(9):
    for j in range(9):
        N[i+1][j+1] = N0[i][j]

#問題の作成       
prob = LpProblem("Sudoku Problem",LpMinimize)

#範囲1から9の整数変数x[i][j]たちの作成
x = Variable.dicts("x", (range(1,10), range(1,10)), 1, 9, LpInteger)

#論理式をMILPの制約に変換するためのクラス
lg = LogicTranslator()

#各行には1から9の数字がちょうど一回ずつ現れる。
lg += forall(range(1,10), lambda i:
        forall(range(1,10), lambda k:
            exists(1)(range(1,10), lambda j:
                x[i][j] == k )))

#各列には1から9の数字がちょうど一回ずつ現れる。
lg += forall(range(1,10), lambda j:
        forall(range(1,10), lambda k:
            exists(1)(range(1,10), lambda i:
                x[i][j] == k )))

#3x3で区切られた各ブロックには1から9の数字がちょうど一回ずつ現れる。
lg += forall((1,4,7), lambda i:
        forall((1,4,7), lambda j:
            forall(range(1,10), lambda k:
                exists(1)((0,1,2), lambda q:
                    exists(1)((0,1,2), lambda r:
                        x[i+q][j+r] == k )))))

#初期値
lg += forall(range(1,10), lambda i:
        forall(range(1,10), lambda j:
            (N[i][j]>=1) >> (x[i][j]==N[i][j]) ))

#目的関数はなんでも良い。
prob += 0

#変換した制約式を問題に与える。
for e in lg:
    prob += e

#問題を解く
prob.solve()

#結果を出力
for i in range(1,10):
    for j in range(1,10):
        print('{} '.format(int(x[i][j].value())),end='')
    print()
