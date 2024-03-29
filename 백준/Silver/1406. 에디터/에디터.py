import sys
front_str = list(sys.stdin.readline().rstrip())
# M = int(input())

end_str = []

for _ in range(int(sys.stdin.readline())):
    command = list(sys.stdin.readline().split())
    if command[0] == 'L':
        if front_str:
            end_str.append(front_str.pop())
    elif command[0] == 'D':
        if end_str:
            front_str.append(end_str.pop())
    elif command[0] == 'B':
        if front_str:
           front_str.pop()
    elif command[0] == 'P':
        front_str.append(command[1])
        
# strings = front_str + end_str[::-1]
# print(('').join(strings))
front_str.extend(reversed(end_str))
print(''.join(front_str))