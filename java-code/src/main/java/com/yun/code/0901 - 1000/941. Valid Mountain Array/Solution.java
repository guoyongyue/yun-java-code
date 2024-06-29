class Solution {
    public boolean validMountainArray(int[] arr) {
        int n = arr.length;
        int i = 0;
        int j = n - 1;
        while (i < n - 1 && arr[i + 1] > arr[i]) {
            i++;
        }
        while (j > 0 && arr[j - 1] > arr[j]) {
            j--;
        }
        return i > 0 && j < n - 1 && i == j;
    }
}
