def neighbourhood(knapsacks,items):
    # search neighbourhoods of current solution
    #the definition of neighbour is: move one item's from a knapsack (or not included) to another knapsack 
    Fx = []
    for i in range(len(items)):
        for j in range(len(knapsacks)):
            if items[i][2] != j: 
                # search the neighbours but not include itself
                temp = items[i][2] 
                items[i][2] = j  
                # move items[[Xn[i]][1]] to knapsack j 
                
                totalValue = 0      
                # calculate value for current solution.
                for item in items:
                    if item[2] != -1:
                        totalValue += item[0]
                totalWeight = 0
                for item in items:
                    if item[2] == j :
                        totalWeight += item[1]
                if knapsacks[j] >= totalWeight:
                    Xtemp = []
                    for item in items:
                        Xtemp.append([item[0],item[1],item[2]])
                    Fx.append([Xtemp,totalValue])
                else:
                    index = i
                    minVal = items[i][0]
                    for k in range(len(items)):
                        if items[k][2] == j and items[k][0] < minVal:
                            index = k
                            minVal = items[k][0]
                    totalValue -= items[index][0]
                    if index != i and totalWeight - items[index][1] <= knapsacks[j]:
                        items[index][2] = -1
                        Xtemp = []
                        for item in items:
                            Xtemp.append([item[0],item[1],item[2]])
                        Fx.append([Xtemp,totalValue])
                    
                items[i][2] = temp
    return Fx

def bestNeighbourhood(Fx,knapsacks,items): 
    # find the best neighbour with the largest value in all neighbours
    if len(Fx) == 0:
        return [],0
    
    bestIndex = -1
    maxTotalValue = 0
    for i in range(len(Fx)):
        if Fx[i][1] > maxTotalValue:
            maxTotalValue = Fx[i][1]
            bestIndex = i
    return Fx[bestIndex][0],maxTotalValue
    
def neighbourhoodSearchForMultipleKnapsack(knapsacks,items):
    print("\033[1;35mneighbourhood search algorithm for multiple knapsacks --- start->")
    print('input:')
    print('knapsacks: \n{}'.format(knapsacks))
    print('items: \n{}\n\033[0m'.format(items))
    
    currentBestValue = 0 
    # record the total value with current solution
    for item in items:
        if item[2] != -1:
            currentBestValue += item[0]
            
    bestNeighbourhoodValue = currentBestValue + 1 # record the best values of all neighbourhoods
    firstR = True
    print("\033[1;36mwe choose the input items as our start solution. it's: \n{}\033[0m".format(items))
    input()
    count = 0
    bestSolution = []
    for item in items:
        bestSolution.append(item[:])
        
    while currentBestValue < bestNeighbourhoodValue: 
        #termination condition: current value equals the best value of all of its neighbours
        currentBestValue = bestNeighbourhoodValue
        if firstR:
            firstR = False
            currentBestValue -= 1
        items = []
        for item in bestSolution:
            items.append(item[:])
        print("\033[1;32miteration {}:".format(count))
        print("------------------------\n\033[0m")
        print("\033[1;36mthe current solution is: \n{}".format(bestSolution))
        print("for this solution, the total value is: {}\n\033[0m".format(currentBestValue))
        nbhs = neighbourhood(knapsacks, items)
        print("and all it's feasible {} neighbours are:".format(len(nbhs)))
        input()
        for nbh in nbhs:
            print("solution is: \n{}".format(nbh[0]))
            print("value is {}\n".format(nbh[1]))
        bestSolution,bestNeighbourhoodValue = bestNeighbourhood(nbhs, knapsacks, items)
        print("\033[1;34mso we choose the neighbour \n{}\nas our next solution, value is {}\033[0m".format(bestSolution,bestNeighbourhoodValue))
        count+=1
        input()
    else:
        print("\033[1;33mbut we have found that even the best neighbour is not better than the current solution, so we end the loop\n\033[0m")
    print("\033[1;35m\n****************\033[0m") 
    print("\033[1;35m<-algorithm end\n\033[0m")
    
    return items,currentBestValue