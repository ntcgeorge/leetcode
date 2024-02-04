import java.util.Scanner;

public class Question4 {
// find the mid of each array, compare it, and move to the correspondding halves.
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        if( m == 0) return n % 2 == 0 ? (nums1[n / 2] + nums1[n/2 - 1]) / 2.0 : nums1[n / 2];
        if( n == 0) return m % 2 == 0 ? (nums2[m / 2] + nums2[m/2 - 1]) / 2.0 : nums2[m / 2];
        int lo1 = 0;
        int lo2 = 0;
        int hi1 = n - 1;
        int hi2 = m - 1;
        while(lo1 < hi1 && lo2 < hi2) {
            int mid1 = lo1 + (hi1 - lo1) / 2;
            int mid2 = lo2 + (hi2 - lo2) / 2;
            if(nums1[mid1] > nums2[mid2]){
                hi1 = mid1;
                lo2 = mid2;
            }
            else if(nums1[mid1] < nums2[mid2]) {
                lo1 = mid1;
                hi2 = mid2;
            }
            else {
                return nums1[mid1];
            }
        }
        return (n + m) % 2 == 0 ?  (nums1[lo1] + nums2[lo2]) / 2.0 : Math.min(nums1[lo1], nums2[lo2]);

    }


    public static void main(String[] args) {
        int[] nums1 = new int[]{2,2,4,4};
        int[] nums2 = new int[]{2,2,4,4};
        Question4 q = new Question4();
        System.out.println(q.findMedianSortedArrays(nums1, nums2));

    }
}
