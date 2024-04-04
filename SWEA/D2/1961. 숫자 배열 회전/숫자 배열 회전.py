T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    # ///////////////////////////////////////////////////////////////////////////////////
    N = int(input())
    numbers = []
    for i in range(N):
        numbers.append(list(input().split()))

    print(f"#{test_case}")
    for i in range(N):
        num_90 = ""
        num_180 = ""
        num_270 = ""
        for j in range(N):
            num_90 += numbers[j][N-i-1]
            num_180 += numbers[N-i-1][N-j-1]
            num_270 += numbers[N-j-1][i]
        print(num_270, num_180, num_90)

    # ///////////////////////////////////////////////////////////////////////////////////