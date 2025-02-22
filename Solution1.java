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