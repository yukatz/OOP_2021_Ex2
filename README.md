

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
![UMLGraphs](https://user-images.githubusercontent.com/92925727/146197322-ed01c7c8-10bd-469a-8951-21763aa2a2b6.jpeg)



## ***Results:***

| Number of Nodes  | Is connected | Shortest Path  | Shortest Path Distenation | Center  |  Tsp  |
| ---------------  | ------------ | -------------  | ------------------------- | ------- |-----  |
|   1,000 Nodes    |    761 ms    |      58 ms     |        56 ms              |  3 sec  |70 ms  |
|   10,000 Nodes   |    18 sec    |     133 ms     |       302 sec             |  Long   | 396 ms|
|  100,000 Nodes   |  Tooo Long   |      6 sec     |       22 sec              |Tooo Long| 31 sec|
| 1,000,000 Nodes  |   *I have a laptop not a spaceship*|

### ***As you can see, Center function takes a long time becouse the algorithm running asymptote is O(Vertex(Vertex+Edges)^2)and vertex number grounin, the time is grouning.***

##How to Running the GUI:
Open a folder, enter to this folder the graphs you want to load and the file 'Ex2.jar'. 
In the cmd run script: java -jar Ex2.jar .json.

##How to work with the GUI:
After running the script a window will be opened. In the opened window, you will see the printed graph and menu. *To load a new graph into the window, enter the location of the json file on your computer into the text field and press the button "Load".

now it is time to play:
there are some option to edit the graph as:
adding edge from src to dest
removing nodes
removing edges 
and also options to run some algorhitms as:
see if the grap is connected.
find the shortest distance in the graph.
find the shortest path from src to dest.
finding the center of the grap.

it is simple to do as it shown in the video:


https://user-images.githubusercontent.com/80645472/146269661-42c7ec48-4fd3-4c00-8be0-72c256c9b389.mp4


