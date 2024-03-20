grid = []
for _ in range(4):
    grid.append(list(map(int, input().split())))

direction = input()

def move_left():
    tmp = []
    for row in range(4):
        for num in grid[row]:
            if num:
                if tmp and tmp[-1] == num:
                    tmp[-1] *= -2
                else:
                    tmp.append(num)

        tmp = [abs(x) for x in tmp]
        tmp += [0] * (4 - len(tmp))
        grid[row] = tmp
        tmp = []

def move_right():
    for row in grid:
        row.reverse()
    move_left()
    for row in grid:
        row.reverse()

def move_up():
    grid = list(map(list, zip(*grid)))
    move_left()
    grid = list(map(list, zip(*grid)))

def move_down():
    grid = list(map(list, zip(*grid)))
    move_right()
    grid = list(map(list, zip(*grid)))

# direction에 따라 함수 실행
if direction == 'L':
    move_left()
elif direction == 'R':
    move_right()
elif direction == 'U':
    move_up()
elif direction == 'D':
    move_down()
else:
    print('Invalid direction')

for row in grid:
    print(*row)