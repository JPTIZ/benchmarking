cache = {0: 0, 1: 1}

def cached_fibonacci(n):
    if n in cache:
        return cache[n]
    cache[n] = cached_fibonacci(n - 1) + cached_fibonacci(n - 2)
    return cache[n]

def fibonacci(n):
    x, y = 0, 1
    for _ in range(n):
        x, y = x + y, x
    return x

import sys
n = int(sys.argv[-1])

sys.setrecursionlimit(n + 1)

if "-c" in sys.argv or "-r" in sys.argv:
    fn = cached_fibonacci
else:
    fn = fibonacci

print(fn(n))
