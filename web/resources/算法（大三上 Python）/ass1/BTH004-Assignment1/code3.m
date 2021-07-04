def tabuSearch(knapsacks,items,iterTimes,tabuLength):
    print("\033[1;35mneighbourhood search algorithm for multiple knapsacks ---tabu search start->")
    print('input:')
    print('knapsacks: \n{}'.format(knapsacks))
    print('items: \n{}'.format(items))
    print('iteration times: \n{}'.format(iterTimes))
    print('length of tabu list: \n{}\n\033[0m'.format(tabuLength))
    
    currentBestValue = 0 
    # record the total value with current solution
    bestNeighbourhoodValue = 0 
    # record the best values of all neighbourhoods
    for item in items:
        if item[2] != -1:
            currentBestValue += item[0]
    bestNeighbourhoodValue = currentBestValue
    bestSolution = []
    for item in items:
        bestSolution.append(item[:])
    
    print("\033[1;36mwe choose the input items as our start solution. it's: \n{}\033[0m".format(items))
    input()
    
    tabuList = []
    for i in range(iterTimes):
        print("\033[1;32miteration {}:".format(i+1))
        print("------------------------\n\033[0m")
        print("and the current solution is: \n{}".format(bestSolution))
        print("for this solution, the total value is: {}\n\033[0m".format(bestNeighbourhoodValue))
        
        solution = []
        for item in bestSolution: 
            #deep copy
            solution.append(item[:])
        if i < tabuLength:
            tabuList.append(solution)
        else:
            tabuList[i % tabuLength] = solution
        print("update the tabu list")
        print("and now the list is :\n")
        for tabu in tabuList:
            print("\033[1;31m{}\033[0m".format(tabu))
        nbhs = neighbourhood(knapsacks, bestSolution)
        for tabuSolution in tabuList: 
            # remove solutions in tabu list 
            for nbh in nbhs:
                if tabuSolution in nbh:
                    nbhs.remove(nbh)
        print("\nand all it's feasible(enough capacity and also not in tabulist) {} neighbours are:".format(len(nbhs)))
        input()
        if len(nbhs) == 0:
            print("\033[1;31mthere are no solution for next iteration. so we end the loop\033[0m")
            break;
        for nbh in nbhs:
            print("solution is: \n{}".format(nbh[0]))
            print("value is {}\n".format(nbh[1]))
        input()
        bestSolution,bestNeighbourhoodValue = bestNeighbourhood(nbhs, knapsacks, items)
        print("\033[1;34mso we choose the neighbour \n{}\nas our next solution, value is {}\033[0m".format(bestSolution,bestNeighbourhoodValue))
        
        print("\033[1;36mthe best solution now is: \n{}\nthe total value is: {}\n".format(items,currentBestValue))
        
        if currentBestValue < bestNeighbourhoodValue:
            items = []
            for item in bestSolution:
                items.append(item[:])
            currentBestValue = bestNeighbourhoodValue
            print("\033[1;34mso we update the best solution, now the best value is {}\n\033[0m".format(currentBestValue))
        else:
            print("\033[1;33mso we do not update the best solution\033[0m")
    print("\033[1;35m\n****************\033[0m")       
    print("\033[1;35m<-algorithm end\n\033[0m")
        
    return items,currentBestValue