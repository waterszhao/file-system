def merge_sort(A):
    if len(A) <= 1:
        return A
    else:
        middle = len(A) // 2
        LA = A[:middle]
        RA = A[middle:]
        LA = merge_sort(LA)
        RA = merge_sort(RA)
        SA = merge(LA,RA)
        return SA

def merge(LA,RA):
    SA = []
    l = 0
    r = 0
    while l < len(LA) and r < len(RA):
        if LA[l] <= RA[r]:
            SA.append(LA[l])
            l += 1
        elif LA[l] >= RA[r]:
            SA.append(RA[r])
            r += 1
    SA += LA[l:]
    SA += RA[r:]
    return SA
