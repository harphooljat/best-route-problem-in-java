**_Steps to follow_**:

* Create cost matrix from lat, long of the nodes using haversine formula

* Example:

                    40
           /--> R1 ---> C1
        10/     | \50  / |
         /      |  \  /  |
        A     30|   \/   |80
         \      | 60/    |
        20\     |  /  \  |
           \--> R2 ---> C2
                    70

* Cost Matrix for above example:

                    A       R1      R2      C1      C2
         *  A       999     10      20      999     999
         *  R1      999     999     30      40      50
         *  R2      999     30      999     60      70
         *  C1      999     999     60      999     80
         *  C2      999     50      999     80      999

**_Possible routes:_**

Cost: 180; Path: {1, 2, 3, 4, 5} <br/>
Cost: 170; Path: {1, 3, 2, 4, 5}

**Path {1, 3, 2, 4, 5} is the best path as the cost is minimum (170) with this path.**

**_Explanation:_**

| 1-->2(10) | 2-->3(30) | 3-->4(60) | 4-->5(80)
|-----------|-----------|-----------|-----------
| (10)      | (40)       | (100)    | (180)

| 1-->3(20) | 3-->2(30) | 2-->4(40) | 4-->5(80)
|-----------|-----------|-----------|-----------
| (20)      | (50)      | (90)      | (170)

**_Note:_**
For the sake of simplicity, I haven't considered time to prepare meals by the restaurants etc.