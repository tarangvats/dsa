public class SegmentTreeLC {

    public static void main(String[] args){
        int[] a = {2,4,6,8};
        SegmentTreeLC st= new SegmentTreeLC(a);
        System.out.println(st.query(1,3));
        st.set(2,1);
        System.out.println(st.query(0,4));
    }

    private final int n;

    private final long[] tree;

    SegmentTreeLC(int[] arr){
        n = arr.length;
        tree = new long[2*n];

        for(int i = 0; i<n ; i++)
            tree[n+i] = arr[i];
        for(int i = n-1; i>0; i--)
            tree[i] = tree[2*i] + tree[2*i + 1];
    }

    void set(int index, long value){
        int p = index + n;
        tree[p] = value;
        while(p>1){
            p/=2;
            tree[p] = tree[2*p] + tree[2*p +1];
        }
    }

    long query(int left, int right){
        long sum = 0;
        left+=n;
        right+=n;

        while(left < right){
            if((left & 1)==1)
                sum = sum + tree[left++];
            if((right & 1)==1)
                sum = sum + tree[--right];
            left/=2;
            right/=2;
        }
        return sum;
    }
}
