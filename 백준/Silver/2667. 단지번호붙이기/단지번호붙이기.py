def dfs(home, i: int, j: int, visit):
    if i < 0 or i >= len(home) or j < 0 or j >= len(home[0]) or home[i][j] != '1':
        return

    visit.append([i, j])
    home[i][j] = '0'

    dfs(home, i, j - 1, visit)
    dfs(home,i, j + 1, visit)
    dfs(home,i - 1, j, visit)
    dfs(home,i + 1, j, visit)


N = int(input())

home = []
for _ in range(N):
    home.append(list(input()))

cnt_list = []
visit = []
for i in range(N):
    for j in range(N):
        if home[i][j] == '0':
            continue
        dfs(home, i, j, visit)
        cnt_list.append(len(visit))
        visit = []

cnt_list.sort()
print(len(cnt_list), *cnt_list, sep='\n')