a=input()
v="aeiouAEIOU"
if a.isalpha():
    if(a in v):
        print("Vowel")
    else:
        print("Consonant")
else:
    print("Invalid")
