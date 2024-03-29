N, K = map(int, input().split())
cash = []
cnt = 0
for _ in range(N):
    cash.append(int(input()))

for i in range(len(cash)-1, -1, -1):
    if cash[i] <= K:
        cnt += (K // cash[i])
        K = K % cash[i]

print(cnt)