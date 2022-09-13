package ru.nsu.ntatarinov;

public class Heapsort{
    public static void siftUp(int n, int[] heap){
        if (n>0)
        {
            if (heap[n]<heap[(n-1)/2])
            {
                int tmp = heap[(n-1)/2];
                heap[(n-1)/2] = heap[n];
                heap[n] = tmp;
                siftUp((n-1)/2, heap);
            }
        }
    }
    public static void siftDown(int n, int k, int[] heap)
    {
        int sonID;
        if (2*n+1<=k)
        {
            if (2*n+1==k)
            {
                sonID = 2*n+1;
            }
            else
            {
                if (heap[2*n+1]<heap[2*n+2])
                    sonID = 2*n+1;
                else
                    sonID = 2*n+2;
            }
            if (heap[n]>heap[sonID])
            {
                int tmp = heap[sonID];
                heap[sonID] = heap[n];
                heap[n] = tmp;
                siftDown(sonID, k, heap);
            }
        }
    }
    public static void add(int x, int k, int[] heap){
        heap[k+1] = x;
        siftUp(k+1,heap);
    }
    public static int extractMin(int k, int[] heap){
        int min = heap[0];
        heap[0] = heap[k];
        siftDown(0, k-1, heap);
        return min;
    }
    public static void main(String[] args){

        int k = 0;
        int N = 10;
        int[] A = {5, 3, 4, 1, 2, 6, 9, 7, 10,8};
        int[]heap = new int[10];
        heap[0] = A[0];
        for (int i = 1; i<N; i++){
            add(A[i], k++, heap);
        }
        for (int i = 0; i < N; i++) {
            System.out.print(extractMin(k--, heap)+"\n");
        }
    }
}