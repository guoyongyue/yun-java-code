package com.yun.code.package0000.package00;

import com.yun.util.InitUtil;

class S04两个排序数组的中位数 {
    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。
     * 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。
     * 你可以假设 nums1 和 nums2 不同时为空。
     * 示例 1:
     * nums1 = [1, 3]
     * nums2 = [2]
     * 中位数是 2.0
     * <p>
     * 示例 2:
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     * 中位数是 (2 + 3)/2 = 2.5
     */
    public double findMedianSortedArrays2(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = iMin + 1; // i is too small
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = iMax - 1; // i is too big
            } else { // i is perfect
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        S04两个排序数组的中位数 s04两个排序数组的中位数 = new S04两个排序数组的中位数();
        int[] A = InitUtil.initIntArray("1,2,4");
        int[] B = InitUtil.initIntArray("3,4,5");
        double medianSortedArrays = findMedianSortedArrays(A, B);
        System.out.println(medianSortedArrays);

    }

    public static double findMedianSortedArrays(int[] A, int[] B) {
        boolean flag = ((A.length + B.length) % 2) == 0;
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            if(A[i]>B[j]){
                j++;
                if((i+j)>((A.length + B.length) / 2)){
                    if(flag){
                        return test(B,j,A,i);
                    }else {
                        return B[j];
                    }
                }
            }else {
                i++;
                if((i+j)>((A.length + B.length) / 2)){
                    if(flag){
                        return test(A,i,B,j);
                    }else {
                        return A[i];
                    }
                }
            }
        }

        return 0D;
    }
    private static Double test(int[] A ,int indexA,int[] B ,int indexB){
        if(indexB>=B.length){
            return Double.valueOf(A[indexA-1]+A[indexA])/2D;
        }else if (indexA>=A.length){
            return Double.valueOf(A[indexA-1]+B[indexB])/2D;
        } else if (A[indexA-1] > B[indexB]) {
            return Double.valueOf(A[indexA-1]+B[indexB])/2D;
        }else{
            return Double.valueOf(A[indexA-1]+A[indexA])/2D;
        }

    }

}