T = 10

# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    # ///////////////////////////////////////////////////////////////////////////////////
    result = 0
    N = int(input())
    buildings = list(map(int, (input().split())))

    for i in range(2, len(buildings)-2):
        max_val = max(buildings[i-2], buildings[i-1], buildings[i+1], buildings[i+2])
        diff = buildings[i] - max_val
        result += diff if diff > 0 else 0

    print(f'#{test_case} {result}')
    # ///////////////////////////////////////////////////////////////////////////////////
