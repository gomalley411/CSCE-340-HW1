# CSCE-340-HW1
/******************************************************************************
 *  Name:     George
 *  NetID:    not sure
 *  Precept:  not sure
 *
 *  Partner Name:    Rachel
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 * 
 *  Operating system: Windows (George)
 *  Compiler: Eclipse (George)
 *  Text editor / IDE:  Eclipse (George)
 *
 *  Have you taken (part of) this course before:
 *  no
 *  Hours to complete assignment (optional):
 *
 ******************************************************************************/

Programming Assignment 1: Percolation


/******************************************************************************
 *  Describe how you implemented Percolation.java. How did you check
 *  whether the system percolates?
 *****************************************************************************/

I interpret this question to mean, what method did I use to check whether a system
percolates or not. In my percolates() method I simply returned myPerc.connected(myTop, myBottom)
where myTop and myBottom are the virtual top and bottom respectively.
I'm not great with words, but I hope that explains it well enough for you. Let me know
if you have any questions.


/******************************************************************************
 *  Perform computational experiments to estimate the running time of
 *  PercolationStats.java for values of n and T when implementing
 *  Percolation.java with QuickFindUF.java.
 *
 *  To do so, fill in the two tables below. Each table must have at least
 *  4 data points, ranging in time from around 0.1 seconds to around
 *  60 seconds. Do not include data points that takes less than 0.1 seconds.
 *****************************************************************************/

(keep T constant)

 n          time (seconds)
------------------------------
...
...
...
...
...


(keep n constant)

 T          time (seconds)
------------------------------
...
...
...
...
...


/******************************************************************************
 *  Using the empirical data from the above two tables, give a formula 
 *  (using tilde notation) for the running time (in seconds) of
 *  PercolationStats.java as function of both n and T, such as
 *
 *       ~ 5.3*10^-8 * n^5.0 T^1.5
 *
 *  Recall that with tilde notation, you include both the coefficient
 *  and exponents of the leading term (but not lower-order terms).
 *  Round each coefficient to two significant digits.
 *
 *****************************************************************************/

running time (in seconds) as a function of n and T:  ~ 


/******************************************************************************
 *  Repeat the previous two questions, but using WeightedQuickUnionUF
 *  (instead of QuickFindUF).
 *****************************************************************************/

(keep T constant)

 n         time (seconds)
------------------------------
...
...
...
...
...


(keep n constant)

 T          time (seconds)
------------------------------
...
...
...
...
...


running time (in seconds) as a function of n and T:  ~ 


/**********************************************************************
 *  How much memory (in bytes) does a Percolation object (which uses
 *  WeightedQuickUnionUF.java) use to store an n-by-n grid? Use the
 *  64-bit memory cost model from Section 1.4 of the textbook and use
 *  tilde notation to simplify your answer. Briefly justify your
 *  answers.
 *
 *  Include the memory for all referenced objects (deep memory).
 **********************************************************************/











 
/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/




/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including
 *  classmates and friends) and attribute them by name.
 *****************************************************************************/
- got help from my partner and from meeting with you on Zoom

/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/




/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 *****************************************************************************/

v
