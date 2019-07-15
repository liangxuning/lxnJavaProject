package codepassage;

public class Quicksort {
    public static void main(String[] args) {
        int[] a = {6,3,2,4,12,32,22,15,11};
        quick_sort(a, 0, 8);
        for (int aa : a) {
            System.out.println(aa);
        }
    }
    static int mpartition(int a[], int l, int r) {
        int pivot = a[l];

        while (l<r) {
            while (l<r && pivot<=a[r]) r--;
            if (l<r) a[l++]=a[r];
            while (l<r && pivot>a[l]) l++;
            if (l<r) a[r--]=a[l];
        }
        a[l]=pivot;
        return l;
    }

    static void quick_sort(int a[], int l, int r) {

        if (l < r) {
            int q = mpartition(a, l, r);
            msort(a, l, q-1);
            msort(a, q+1, r);
        }
    }

    private static void msort(int[] a, int l, int i) {
        int temp = a[l];
        a[l] = a[i];
        a[i] = temp;
    }
}
