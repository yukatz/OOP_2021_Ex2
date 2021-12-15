

# OOP_2021_Ex2
## OOP Course - Exercise N'2 -Directed Weighted Graph Algorithms
***By Yulia Katz ID:324385509  and Avidan Abitbul ID:302298963***

![687474703a2f2f6765656b737461636b2e6e65742f7473702e676966](https://user-images.githubusercontent.com/80645472/146063080-01f5c5a2-47a4-4bfb-b046-320bd75fb702.gif)

## Execution stages
![‏‏PlanforReadmrjpg](https://user-images.githubusercontent.com/92925727/146073760-dab08b91-fa9d-4bf9-a79d-deb71809cc4c.jpg)



## ***Classes:***
- [x] **Node Data**
  > Represents vertex of Directed Weighted Graph - construct of key and location.
- [x] Edge Data
  > Represents edges of Directed Weighted Graph - construct of source, destination and weight.
- [x] Geo Location
  > Represents Graph nodes position by x, y .
- [x] Directed Weighted Graph
  > Represents Graph by Lists of Nodes and Edges. For list we used Hash map. Edge list built from hash map of hash maps.
- [x] Directed Weighted Graph Algorithms:
  > Represents algorithms that can be used on directed graph:
    - **isConnected** - Cheking if  there is a valid path from each node to others.
    - **Shoretest Path Distenation** - Computes the length of the shortest path between source to destination.
    - **Shoretest Path** - Presents the shortest path between source to destination.
    - **Center** - Finds the vertex which minimizes the max distance to all the other nodes
    - **TSP** - *Travelling salesman problem* - Computes a list of consecutive nodes wich go over all the nodes in cities. 
      _- https://en.wikipedia.org/wiki/Travelling_salesman_problem
     
# **UML**

![UML](https://user-images.githubusercontent.com/80645472/146196966-96125f86-bbf0-43f6-a531-8677cd0cd605.png)


## ***Results:***

| Number of Nodes  | Is connected | Shortest Path  | Shortest Path Distenation | Center | Tsp |
| ---------------  | ------------ | -------------  | ------------------------- | -------|-----|
|   1,000 Nodes    | Content Cell |  Content Cell  | Content Cell              | Cell   | Cell|
|   10,000 Nodes   | Content Cell |  Content Cell  | Content Cell              | Cell   | Cell|
|  100,000 Nodes   | Content Cell |  Content Cell  | Content Cell              | Cell   | Cell|
| 1,000,000 Nodes  |              |  Content Cell  | Content Cell              | Cell   | Cell|


