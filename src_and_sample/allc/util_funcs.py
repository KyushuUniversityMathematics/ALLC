import functools as ft
import operator as op
import allc.extension as ex

def sum_(l):
    return ft.reduce(op.add, l, 0)

def all_(l):
    return ft.reduce(op.and_, l, 1)

def any_(l):
    return ft.reduce(op.or_, l, 0)

def forall(l, f):
    return all_(map(f, l))

def exists(*args):
    if len(args) == 1:
        def n_exists(l, f):
            return sum_(map(f,l)) == args[0]
        return n_exists
    elif len(args) == 2:
        return any_(map(args[1], args[0]))
    else:
        raise Exception('exists has one or two inputs')
