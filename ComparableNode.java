import Interfaces.Node_Data;

public class ComparableNode implements Comparable<ComparableNode>{
        Node_Data node;
        double val;

        public ComparableNode(Node_Data n,double v)
        {
            node = n;
            val = v;
        }

        public void setVal(int v)
        {
            val = v;
        }

        @Override
        public int compareTo(ComparableNode n) {
            if(this.val>n.val)
            {
                return 1;
            }
            else if(this.val < n.val)
            {
                return -1;
            }
            return 0;
        }
    }

