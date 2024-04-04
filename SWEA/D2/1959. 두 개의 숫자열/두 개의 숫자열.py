
T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    N, M = map(int, input().split())
    if N < M:
        A = list(map(int, input().split()))
        B = list(map(int, input().split()))
    else:
        B = list(map(int, input().split()))
        A = list(map(int, input().split()))

    # len(A) < len(B)

    max_val = 0
    for i in range(len(B)-len(A)+1):
        sum_val = 0
        for j in range(len(A)):
            sum_val += (B[i+j]*A[j])
        # sum_val += sum(B[:i]) + sum(B[i+len(A):])
        max_val = max(max_val, sum_val)

    print(f'#{test_case} {max_val}')