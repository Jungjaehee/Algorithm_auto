N = int(input())
distance = list(map(int, input().split()))
city = list(map(int, input().split()))
min_value = city[0]
sum = 0

for idx in range(len(distance)):
    min_value = min(min_value, city[idx])
    sum += (min_value * distance[idx])
print(sum)