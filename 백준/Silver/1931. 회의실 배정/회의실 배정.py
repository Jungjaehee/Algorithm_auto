N = int(input())
time_table = []
cnt = 0
last_end = 0
for _ in range(N):
    s, e = map(int, input().split())
    time_table.append((s, e))

time_table.sort(key = lambda x: (x[1], x[0]))

for s, e in time_table:
    if s >= last_end:
        cnt += 1
        last_end = e

print(cnt)
    