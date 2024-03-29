N = int(input())
A_str = input().split()
A = {}
for a in A_str:
    A[int(a)] = 0

M = int(input())
M_list = list(map(int, input().split()))
for m in M_list:
    if m in A:
        print(1)
    else:
        print(0)