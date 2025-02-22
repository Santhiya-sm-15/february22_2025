# february22_2025
The problem that i solved today in leetcode

1.We run a preorder depth-first search (DFS) on the root of a binary tree.At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  If the depth of a node is D, the depth of its immediate child is D + 1.  The depth of the root node is 0. If a node has only one child, that child is guaranteed to be the left child. Given the output traversal of this traversal, recover the tree and return its root.

Code:
class pair
{
    TreeNode n;
    int depth;
    public pair(TreeNode n,int depth)
    {
        this.n=n;
        this.depth=depth;
    }
}
class Solution {
    public TreeNode recoverFromPreorder(String traversal) {
        Stack<pair> st=new Stack<>();
        int i=0,cnt=0;
        String s="";
        int n=traversal.length();
        while(i<n && traversal.charAt(i)!='-')
            s+=traversal.charAt(i++);
        int val=Integer.parseInt(s);
        TreeNode root=new TreeNode(val);
        st.push(new pair(root,0));
        while(i<n)
        {
            if(traversal.charAt(i)=='-')
            {
                cnt++;
                i++;
            }
            else
            {
                s="";
                while(i<n && traversal.charAt(i)!='-')
                    s+=traversal.charAt(i++);
                val=Integer.parseInt(s);
                int dep=cnt;
                while(!st.isEmpty() && st.peek().depth>=dep)
                    st.pop();
                TreeNode x=st.peek().n;
                TreeNode nn=new TreeNode(val);
                if(x.left==null)
                    x.left=nn;
                else
                    x.right=nn;
                st.push(new pair(nn,dep));
                cnt=0;
            }
        }
        return root;
    }
}
