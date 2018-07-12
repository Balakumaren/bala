a=input()
a=a.split(" ")
a[0]=int(a[0])
a[1]=int(a[1])
a[2]=int(a[2])
if(a[0]>a[1]):
    if(a[0]>a[2]):
        print(a[0])
    else:
        print(a[2])
else:
    if(a[1]>a[2]):
        print(a[1])
    else:
        print(a[2])
