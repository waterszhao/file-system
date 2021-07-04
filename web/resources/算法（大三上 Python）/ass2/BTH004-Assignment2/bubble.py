def bubble_sort(A):
    for i in range(1,len(A)-1):
        for j in range(len(A)-i):
            temp = A[j]
            if A[j] > A[j + 1]:
                A[j] = A[j+1]
                A[j+1] = temp
    return A