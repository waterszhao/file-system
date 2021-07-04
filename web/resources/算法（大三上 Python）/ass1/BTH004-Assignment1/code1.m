def greedyForMultipleKnapsack_firstFit(knapsacks,items):
# input:
# @para knapsacks -> [1,2] represent 2 knapsacks with (weight) capacity 1,2
# @para items -> [[1,2,-1],[3,4,0]] represent 2 items 
# items[0] means this item worth 1 unit, weigh 2 but not include in any kanpsack,
# while item[1] means this item worth 3 unit, weigh 4 unit and is included in knapsacks[0]
    print("\033[1;35mgreedy algorithm for multiple knapsacks --- first fit start->")
    print('input:')
    print('knapsacks: \n{}'.format(knapsacks))
    print('items: \n{}\n'.format(items))

    valuesPerUnit = [] 
    #used to record the value per weight unit for item and item index.
    leftCapacity = knapsacks.copy() 
    #we will modify the list so copy a new knapsacks to avoid to modify the original
    for i in range(len(items)): 
        #fill the lists mentioned above
        valuesPerUnit.append([items[i][0]/items[i][1],i])
    print('and the value per weight unit with the relevant item id are: \n{}'.format(valuesPerUnit))
    
    valuesPerUnit.sort(reverse=True) 
    #descending sort by the value per unit
    print('after sort, it becomes: \n{}'.format(valuesPerUnit))
    print("\nnow we start allocate knapsack for each items in the order of the value per unit ->\033[0m")
    totalValue = 0 
    # record the total values of these items have included
    for i in range(len(items)): 
        #allocate knapsack for the items in the order of values per unit
        isInclude = False
        print("\033[1;32m------------------------\n\033[0m")
        print("\033[1;32mnow allocate knapsack for items[{}]\n\033[0m".format(valuesPerUnit[i][1]))
        print("\033[1;36mknapsacks' condition:{}\n\033[0m".format(leftCapacity))
        for j in range(len(knapsacks)): 
            #search rest space in knapsack for the item
            print("for knapsacks[{}], it still has  {}  weight left".format(j,leftCapacity[j]))
            print("and items[{}] weight  {} ".format(valuesPerUnit[i][1],items[valuesPerUnit[i][1]][1]))
            input()
            if items[valuesPerUnit[i][1]][1] <= leftCapacity[j]: 
                # if have enough space for the current item
                print("\033[1;34mit has enough space for the item, so we include it in this knapsack\n\033[0m")
                totalValue += items[valuesPerUnit[i][1]][0] 
                # add the item value to the total
                leftCapacity[j] -= items[valuesPerUnit[i][1]][1] 
                # it take up a part of space of the current knapsack,so we decrease the room 
                items[valuesPerUnit[i][1]][2] = j 
                # record its position
                isInclude = True
                break 
            # current item have found its place,next item      
            else:
                print("\033[1;33mknapsacks[{}] not has enough space for this item\n\033[0m".format(j))
        if not isInclude:
            print("\033[1;31mall knapsacks have no space for this item!\n\033[0m")
    print('\033[1;35mall items have been allocated!')
    print("\033[1;35m\n****************") 
    print("<-algorithm end")
    
    print('{} {}\033[0m'.format(items,totalValue))
    return items,totalValue

def greedyForMultipleKnapsack_bestFit(knapsacks,items):
    print("\033[1;35mgreedy algorithm for multiple knapsacks --- best fit start->")
    print('input:')
    print('knapsacks: \n{}'.format(knapsacks))
    print('items: \n{}\n'.format(items))
    
    valuesPerUnit = [] 
    #used to record the value per weight unit for item and item index
    leftCapacity = knapsacks.copy() 
    #we will modify the list so copy a new knapsacks to avoid to modify the original
    for i in range(len(items)): 
        #fill the lists mentioned above
        valuesPerUnit.append([items[i][0]/items[i][1],i])
    print('and the value per weight unit with the relevant item id are: \n{}'.format(valuesPerUnit))
    
    valuesPerUnit.sort(reverse=True) 
    #descending sort by the value per unit
    print('after sort, it becomes: \n{}'.format(valuesPerUnit))
    print("\nnow we start allocate knapsack for each items in the order of the value per unit ->\033[0m")

    totalValue = 0 
    # record the total values of these items have included
    for i in range(len(items)): 
        #allocate knapsack for the items in the order of values per unit
        difference = max(leftCapacity) 
        # used to record the best fit diiference, initalize with the largest left rooms in these knapsacks
        knapIndex = -1 
        # used to record the best fit diiference
        print("\033[1;32m------------------------\n\033[0m")
        print("\033[1;32mnow allocate knapsack for items[{}]\n\033[0m".format(valuesPerUnit[i][1]))
        print("\033[1;36mknapsacks' condition:{}\n\033[0m".format(leftCapacity))
        for j in range(len(knapsacks)): 
            #search rest space in knapsack for the item
            print("for knapsacks[{}], it still has  {}  weight left".format(j,leftCapacity[j]))
            print("and items[{}] weight  {} ".format(valuesPerUnit[i][1],items[valuesPerUnit[i][1]][1]))
            input()
            newDifference = leftCapacity[j] - items[valuesPerUnit[i][1]][1]
            if newDifference >= 0:
                print("\033[1;36mknapsacks[{}] it has enough space for this item\n\033[0m".format(j))
                if newDifference < difference: 
                    #if found a new better fit in knapsacks, choose it as the best fit
                    print("\033[1;36mand it's more appropriate for this item\n\033[0m")
                    knapIndex = j
                    difference = newDifference
                else:
                    print("\033[1;33mbut it's not better than current one\n\033[0m")
            else:
                print("\033[1;33mknapsacks[{}] not has enough space for this item\n\033[0m".format(j))
        
        if knapIndex != -1:
            print("\033[1;34mso we include it in knapsacks[{}](weigh {} capacity {})\n\033[0m".format(knapIndex,items[valuesPerUnit[i][1]][1],knapsacks[knapIndex]))
            totalValue += items[valuesPerUnit[i][1]][0] 
            # add the item value to the total
            leftCapacity[knapIndex] -= items[valuesPerUnit[i][1]][1] 
            # it take up a part of space of the current knapsack,so we decrease the room 
        else:
            print("\033[1;31mall knapsacks have no space for this item!\n\033[0m")
        items[valuesPerUnit[i][1]][2] = knapIndex
        
    print('\033[1;35mall items have been allocated!')
    print("\033[1;35m\n****************") 
    print("<-algorithm end")
    
    print('{} {}\033[0m'.format(items,totalValue))
    return items,totalValue