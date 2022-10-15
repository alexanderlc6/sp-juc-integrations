import pendulum

dt_1 = pendulum.datetime(2022, 1, 1, tz = 'Asia/Shanghai')
dt_2 = pendulum.datetime(2022, 1, 1, tz = 'America/Vancouver')

print(dt_2.diff(dt_1).in_hours())
