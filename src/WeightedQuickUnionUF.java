public class WeightedQuickUnionUF {
    private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of elements in subtree rooted at i
    private int count;      // number of components

    // initialize an empty union-find data structure with n elements 0 through n-1.
    // initially, each elements is in its own set.
    public WeightedQuickUnionUF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    // return the number of sets.
    public int count() {
        return count;
    }
  
    // return the canonical element of the set containing element p.
    public int find(int p) {
        validate(p);
        
        while (p != parent[p])
            p = parent[p];
            
        return p;
    }

    // return true if the two elements are in the same set.
    public boolean connected(int p, int q) {
        return find(p) == find(q); // thanks Rachel for letting me know this way was easier, I blanked lol
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));  
        }
    }

    // merge the set containing element p with the set containing element q.
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        
        if (rootP == rootQ) 
            return;

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        
        count--;
    }
}
