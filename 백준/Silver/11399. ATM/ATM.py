N = int(input())
person = list(map(int, input().split()))
sum = 0
person.sort()
for i in range(N):
    sum += (N-i)*person[i]
    
print(sum)