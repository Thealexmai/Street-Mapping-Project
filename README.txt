1. Alex Mai
amai2@u.rochester.edu
Partner: None
Class: CSC 172
Lab TA: Gabe
Lab Session: MW
Assignment: Project 4

2. For this project, I essentially used the lab that I created in Lab20. However, the major thing that I had to learn was the data structures. After having Monroe county taking 12 seconds to run, I quickly learned that using arrays and 2-D arrays were not the best data structures (because adjacency matrixes have to consider all of the different edges). Thus, I spent most of my time trying to learn the different Java data structures that were available for usage. For example, I use Hash Maps to store the vertices and edges, which were originally in arrays. And I used to have a 2-D array to check for connecting vertices, but instead I had to ditch that idea and use an adjacency list, which I chose to use a Linked List to represent, and I just had to point it two ways. The biggest challenge for me was drawing the graph. I originally had a null pointer exception in Graph.java; I don’t remember where exactly, but I imagine that it was in dijkstra’s algorithm. Also, for prim’s algorithm I wasn’t sure where to start, so I hardcoded it and said that it would always begin at Vertex i1.

Run-Time Analysis:

	1) Edge.java: compareTo method: That takes O(1) because of the if statements just testing if they’re less than, equal to, or greater than. Also there’s a weightRoad method: That takes O(1) as well because all it does is call on the Math.sqrt and Math.pow function in the java library and it can be calculated in constant time.	

	2) Vertex.java: The only running method here is the compareTo method, and like the one before, it should only take constant time operation.
	
	3) DrawPanel.java: The only method running in here is the paintComponent method. There are a lot of referencing done here so those all take just O(1), constant time operation. But there is the first for loop that iterates through the entire Hash Map of the Edges. Since it iterates through ALL of them, it has a O(n) run time. And after I escape the for loop, I enter another one for the drawing of lines in between vertices and this iterates through the entire pathKeeper ArrayList, so it has a O(n) run time as well. And then when I exit that for loop and enter the edgeKeeper for loop. That takes O(n) as well because I iterate only through all the edges inside of the array list.

	4) Graph.java: There is a lot of instantiating in the beginning, which take only constant time operation. It is important to note that lookup (.get(key)) for hash maps is constant time operation. But first let’s analyze the createFromFile method. I scan the items and then put them into hash maps and array lists. This takes constant time operation O(1). I call on the Java collection sort to find the minimum and maximum of longitudes and latitudes. This collection sort, according to the Java oracle website, uses something similar to merge sort, so the runtime is expected to be O(nlogn). For dijkstra’s method I have a for loop that iterates through for all vertices inside the vertex hash map. This obviously will take O(n) time since we’re going through all the vertices. And adding the vertex into the priority queue will take O(1). After exiting the for loop, I have a while loop that continues to run until the priority queue is empty. So this essentially means that it’s just peeking at the top of the list and seeing if something is there. It will run O(1). Inside of this while loop I have a for loop that runs O(n) because it iterates through my adjacency list (linked list of vertices connecting the vertices of the hash map). And each check of the if statements inside the of the for loop takes constant time, and so does adding the vertices into the priority queue. That’s the end of the dijkstra’s method. Moving onto the print method for dijkstra’s. I run dijkstra’s (which the run time is listed above), and the Big-Oh of this comes from the for loop that iterates through the entire pathKeeper until it reaches a size of 0. This means that the Big-Oh of this method is O(n). Now let’s analyze prim’s algorithm. I first start off with a for loop saying that  for each edge e that are neighbors of the vertex’s linked list, then add that edge to the priority queue. This takes O(n) because I have to iterate through all of the edges. And my while loop takes O(1), with a nested if statement that also takes O(1). However still inside of this while loop, we have another if statement. And if the destination vertex is not known, then for all edges v till the end of the destination vertex’s adjacent edges, if these vertices’ destination vertices are unknown, the add it to the priority queue. This means that it has a O(n) run time because the for loop takes n runtime while the others are constant time. In the printPrim method, I have a for loop that iterates until the array list to print is empty, so this takes O(n).

	5) Project4Test.java: The worse case scenario comes from the for loop of args.length. The runtime for that is O(n) because I iterate through the entire args to check if they are equivalent to -show, -meridianmap, or -directions. 

3. Extract all files to desktop, open terminal, cd desktop, javac Project4Test.java Edge.java Vertex.java Graph.java DrawPanel.java, java Project4Test [map name] [-show] [-directions StartIntersection endIntersection] [-meridianmap]

NOTE: BE SURE TO INCLUDE THE HYPHEN. Ex: -show

java YourProgramName map.txt -show
java YourProgramName map.txt -directions i1 i2
java YourProgramName map.txt -meridianmap
java YourProgramName map.txt -show -directions i1 i2
java YourProgramName map.txt -show -meridianmap

Files Included:
nys.txt
monroe.txt
ur_campus.txt

Sources:
CSUG - Pseduocode for Prim’s algorithm provided
http://en.wikipedia.org/wiki/Dijkstra%27s_algorithm#Using_a_priority_queue
http://docs.oracle.com/javase/7/docs/api/java/util/Collections.html
