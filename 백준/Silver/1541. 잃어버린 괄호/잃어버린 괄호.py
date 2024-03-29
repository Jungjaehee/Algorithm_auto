eq = input()

number=""
sum=0
minus=False

for s in eq:
    if s >= '0' and s <= '9':
        number += s
        continue
    sum += (-int(number) if minus else int(number))
    number = ""
    if s == '-':
        minus = True

sum += (-int(number) if minus else int(number))
print(sum)