
/**
 * There a list of Recursive excersice from the open university
 * to use maximaize the usage click ctrl f and write the name of function you need
 * to click ctrl f and find the excersice by function name
 * @author (Or saban)
 * @version (a version number or a date)
 */
public class RecursiveExams
{
    /**
     * Finds the length of the longest almost palindrome subsequence in an array.
     * An almost palindrome subsequence is a sequence that can be made into a palindrome
     * by changing at most one element. 
     *
     * @param arr The input array
     * @return The length of the longest almost palindrome subsequence
     */
    public static int longestAlmostPalindrom(int[] arr) {
        // Check if the array is empty or null
        if (arr == null || arr.length == 0) {
            return 0; // If empty, return 0
        }
        // Call the helper function to start the recursion
        return longestAlmostPalindrom(arr, 0, arr.length - 1, 0);
    }

    /**
     * Helper function to recursively find the longest almost palindrome subsequence.
     *
     * @param arr   The input array
     * @param left  The left index of the current subsequence
     * @param right The right index of the current subsequence
     * @param size  The size of the current subsequence
     * @return The length of the longest almost palindrome subsequence
     */
    private static int longestAlmostPalindrom(int[] arr, int left, int right, int size) {
        // If the left index exceeds the right index, we have traversed the subsequence
        if (left > right) {
            return size; // Return the size of the subsequence
        }
        // If the subsequence is almost a palindrome (differing by at most one element)
        if (size >= 2 && right - left == 1) {
            return size + 2; // Return the size of the subsequence plus 2 (adding the adjacent elements)
        }
        // If both indexes point to the same element
        if (left == right) {
            return size + 1; // Return the size of the subsequence plus 1 (counting the single element)
        } else if (arr[left] == arr[right]) {
            // If the elements at the current indexes are the same
            // Try continuing with both elements included
            int takeAndContinueWithBoth = longestAlmostPalindrom(arr, left + 1, right - 1, size + 2);
            // Try continuing with the left element excluded
            int continueWithLeft = longestAlmostPalindrom(arr, left + 1, right, 0);
            // Try continuing with the right element excluded
            int continueWithRight = longestAlmostPalindrom(arr, left, right - 1, 0);
            // Return the maximum length among the three possibilities
            return Math.max(takeAndContinueWithBoth, Math.max(continueWithLeft, continueWithRight));
        }
        // If the elements at the current indexes are different
        // Try continuing with the left element excluded
        int continueWithLeft = longestAlmostPalindrom(arr, left + 1, right, 0);
        // Try continuing with the right element excluded
        int continueWithRight = longestAlmostPalindrom(arr, left, right - 1, 0);
        // Return the longest length among the two possibilities
        return Math.max(continueWithRight, continueWithLeft);
    }

    /////////////////------------------------------------///////////////////////

    /**
     * Recursive function to find the minimal string between two input strings.
     *
     * @param str1 The first input string.
     * @param str2 The second input string.
     * @return The minimal string between str1 and str2.
     */
    private static String minimalSt(String str1, String str2) {
        if (str1.length() == 0) { //if the length is 0 than we done one or both of them
            return str2;
        }
        if (str2.length() == 0) { //if the length is 0 than we done one or both of them
            return str1;
        }
        if (str1.charAt(0) == str2.charAt(0)) {
            //if equals i want substring both
            return str1.charAt(0) + minimalSt(str1.substring(1), str2.substring(1));
        }
        //check both paths and return the minimal one.
        String str1Path = str1.charAt(0) + minimalSt(str1.substring(1), str2);
        String str2Path = str2.charAt(0) + minimalSt(str1, str2.substring(1));
        if (str1Path.length() < str2Path.length()) {
            return str1Path;
        }
        return str2Path;
    }
    ////////////------------------------------------///////////////////////

    /////////////////------------------------------------///////////////////////
    /////////////////------------------------------------///////////////////////
    /**
     * Recursive function to count the number of valid pairs of parentheses.
     * This exercise is from 2023A moed 65.
     *
     * @param n The number of pairs of parentheses to consider.
     * @return The number of valid pairs of parentheses.
     */
    public static int countPairs(int n) {
        return countPairs(n, 0, 0, "");
    }

    // Recursive helper function to count the number of valid pairs of parentheses
    private static int countPairs(int n, int left, int right, String sequence) {
        // If both left and right parentheses counts are equal to n, a valid sequence is found
        if (left == n && right == n) {
            System.out.println(sequence); // Output the valid sequence (optional)
            return 1; // Return 1 to indicate a valid sequence is found
        }
        // If the number of right parentheses is greater than left or either count exceeds n, the sequence is invalid
        if (left > n || right > n || right > left) {
            return 0; // Return 0 to indicate an invalid sequence
        }
        // Recursively explore two paths: adding a left parenthesis or a right parenthesis
        int pathLeft = countPairs(n, left + 1, right, sequence + '(');
        int pathRight = countPairs(n, left, right + 1, sequence + ')');
        // Return the sum of valid sequences found from both paths
        return pathLeft + pathRight;
    }
    //////////// 
    /////////////////////////////
    /**
     * Recursive function to calculate expressions using the given number, result, and maximum number of operations.
     * This exercise is from 2022B moed 96.
     *
     * @param num    The number to use in the expressions.
     * @param result The target result of the expressions.
     * @param maxOp  The maximum number of operations allowed.
     * @return The number of valid expressions that yield the target result.
     */
    public static int calc(int num, int result, int maxOp) {
        return calc(num, result, maxOp, "", num);
    }

    // Recursive helper function to calculate expressions
    private static int calc(int num, int result, int maxOp, String validOp, int sum) {
        // If the maximum number of operations is exceeded, return 0
        if (maxOp < 0) {
            return 0;
        }
        // If the current sum equals the target result, print the expression and return 1
        if (sum == result) {
            System.out.println(validOp + num + " = " + result);
            return 1;
        }
        // Recursively explore four paths: addition, subtraction, multiplication, and division
        int pathAdd = calc(num, result, maxOp - 1, validOp + num + " + ", sum + num);
        int pathSubt = calc(num, result, maxOp - 1, validOp + num + " - ", sum - num);
        int pathDouble = calc(num, result, maxOp - 1, validOp + num + " * ", sum * num);
        int pathDiv = calc(num, result, maxOp - 1, validOp + num + " / ", sum / num);
        // Return the sum of valid expressions found from all paths
        return pathAdd + pathSubt + pathDouble + pathDiv;
    }
    //////////////////////----------------------/////////////////

    /**
     * Recursive function to find the cheapest route among different stations.
     * This exercise is from 2022B moed 96.
     *
     * @param stations The array representing the cost of each station.
     * @param step1    The step size for the first type of movement.
     * @param step2    The step size for the second type of movement.
     * @param limit    The limit on the number of steps.
     * @return The cost of the cheapest route.
     */
    public static int cheapRt(int[] stations, int step1, int step2, int limit) {
        return cheapRt(stations, step1, step2, limit, 0, "", 0);
    }

    // Recursive helper function to find the cheapest route
    private static int cheapRt(int[] stations, int step1, int step2, int limit, int i, String path, int sum) {
        // If the index is out of bounds or the limit is negative, return the maximum value
        if (i > stations.length - 1 || limit < 0) {
            return Integer.MAX_VALUE;
        }
        // If the current station is the last one, return the sum
        if (i == stations.length - 1) {
            sum += stations[i];
            System.out.println(path + i + "\t= " + sum);
            return sum;
        }
        // Recursively explore two paths: moving with step1 and moving with step2
        int step1Path = cheapRt(stations, step1, step2, limit, i + step1, path + i + "\t", sum + stations[i]);
        int step2Path = cheapRt(stations, step1, step2, limit - 1, i + step2, i + "\t", sum + stations[i]);
        // Find the minimum cost among the two paths
        int minPath = Math.min(step1Path, step2Path);
        // If the minimum path cost is negative, return the minimum value
        if (minPath < 0) {
            return Integer.MIN_VALUE;
        }
        return minPath;
    }

    ///////////////////////
    /**
     * Recursive function to find the maximum path sum in a matrix.
     * This exercise is from 2022B moed 86.
     *
     * @param mat The input matrix.
     * @return The maximum path sum in the matrix.
     */
    public static int maxPath(int[][] mat) {
        return maxPath(mat, 0, 0);
    }

    // Recursive helper function to find the maximum path sum
    private static int maxPath(int[][] mat, int i, int j) {
        // If we've reached the bottom-right corner, return the value at this cell
        if (i == mat.length - 1 && j == mat[0].length - 1) {
            return mat[i][j];
        }
        // If we're out of bounds, return a minimum value
        if (i >= mat.length || j >= mat[0].length) {
            return Integer.MIN_VALUE;
        }
        // Calculate two possible paths by considering the two digits in the current cell
        int path1 = mat[i][j] + maxPath(mat, i + mat[i][j] % 10, j + mat[i][j] / 10);
        int path2 = mat[i][j] + maxPath(mat, i + mat[i][j] / 10, j + mat[i][j] % 10);
        // Return the maximum of the two paths
        return Math.max(path1, path2);
    }
    ///////////////////////

    /**
     * Recursive function to find the length of the path in a matrix that matches a given pattern.
     * This exercise is from 2022B moed 94.
     *
     * @param mat     The input matrix.
     * @param pattern The pattern to match.
     * @return The length of the path in the matrix that matches the pattern.
     */
    public static int lengthPath(char[][] mat, String pattern) {
        boolean[][] followMat = new boolean[mat.length][mat[0].length];
        return lengthPath(mat, pattern, 0, 0, followMat);
    }

    // Recursive helper function to find the length of the path that matches the pattern
    private static int lengthPath(char[][] mat, String pattern, int i, int j, boolean[][] followMat) {
        // If the indices are out of bounds, the current character does not match the pattern,
        // or the cell has already been visited, return 0
        if (i < 0 || j < 0 || i > mat.length - 1 || j > mat[0].length - 1 ||
        !findChar(pattern, mat[i][j], 0) || followMat[i][j]) {
            return 0;
        }
        // Mark the current cell as visited
        followMat[i][j] = true;
        // Recursively explore four directions: up, down, left, and right
        int up = 1 + lengthPath(mat, pattern, i - 1, j, followMat);
        int down = 1 + lengthPath(mat, pattern, i + 1, j, followMat);
        int left = 1 + lengthPath(mat, pattern, i, j - 1, followMat);
        int right = 1 + lengthPath(mat, pattern, i, j + 1, followMat);
        // Return the maximum length among the four directions
        return Math.max(Math.max(up, down), Math.max(left, right));
    }

    // Helper function to check if a character exists in the pattern
    private static boolean findChar(String pattern, char x, int i) {
        // Base case: If the character is not found in the pattern, return false
        if (i > pattern.length() - 1) {
            return false;
        }
        // If the character matches, return true
        if (pattern.charAt(i) == x) {
            return true;
        }
        // Recursively check the next character in the pattern
        return findChar(pattern, x, i + 1);
    }

    //////////////
    //////////////
    /**
     * Recursive function to determine if one string can be transformed into another by jumping a fixed number of characters.
     * This exercise is from 2022 A moed 89.
     *
     * @param str1 The source string.
     * @param str2 The target string.
     * @param step The number of characters to jump in each step.
     * @return True if str1 can be transformed into str2 by jumping a fixed number of characters, otherwise false.
     */
    public static boolean isJump(String str1, String str2, int step) {
        return isJump(str1, str2, step, 0, 0);
    }

    // Recursive helper function to determine if str1 can be transformed into str2 by jumping
    private static boolean isJump(String str1, String str2, int step, int i, int j) {
        // If the length of str1 is less than str2, or if the character at position i in str1 does not match str2[j], return false
        if (str1.length() < str2.length() || i > str1.length() - 1 || str1.charAt(i) != str2.charAt(j)) {
            return false;
        }
        // If j reaches the end of str2, return true
        if (j == str2.length()) {
            return true;
        }
        // Recursively check if str1 can be transformed into str2 by jumping step characters
        return isJump(str1, str2, step, i + step, j + 1);
    }

    ////////////////////////////////

    /**
     * Recursive function to find the minimum number of steps required to transform str1 into str2 by jumping a fixed number of characters.
     *
     * @param str1 The source string.
     * @param str2 The target string.
     * @return The minimum number of steps required to transform str1 into str2. If it's not possible to transform str1 into str2, returns -1.
     */
    public static int strStep(String str1, String str2) {
        return strStep(str1, str2, 1);
    }

    /**
     * Recursive helper function to find the minimum number of steps required.
     *
     * @param str1       The source string.
     * @param str2       The target string.
     * @param countStep  The current step count.
     * @return The minimum number of steps required to transform str1 into str2. If it's not possible to transform str1 into str2 within the given step count, returns -1.
     */
    private static int strStep(String str1, String str2, int countStep) {
        // Check if a valid step can transform str1 into str2
        boolean validStep = isJump(str1, str2, countStep);
        // If a valid step is found, return the current step count
        if (validStep) {
            return countStep;
        }
        // If the step count exceeds the length of str1, return -1
        if (countStep > str1.length() - 1) {
            return -1;
        }
        // Recursively increase the step count and check again
        return strStep(str1, str2, countStep + 1);
    }

    ////////////////////
    /**
     * Recursive function to determine if a square sub-matrix within a larger matrix represents an identity matrix.
     * This exercise is from 2022 A moed 67.
     *
     * @param mat  The matrix containing the sub-matrix to check.
     * @param x    The starting index of the sub-matrix.
     * @param size The size of the square sub-matrix.
     * @return True if the sub-matrix represents an identity matrix, otherwise false.
     */
    public static boolean isIdentity(int[][] mat, int x, int size) {
        return isIdentity(mat, x, size, x, x);
    }

    // Recursive helper function to check if the sub-matrix represents an identity matrix
    private static boolean isIdentity(int[][] mat, int x, int size, int i, int j) {
        // If j reaches the end of the sub-matrix, move to the next row
        if (j == size + x) {
            return isIdentity(mat, x, size, i + 1, x);
        }
        // If i reaches the end of the sub-matrix, return true
        if (i == size + x) {
            return true;
        }
        // Check if the current element satisfies the conditions for an identity matrix
        if ((i != j && mat[i][j] != 0) || (i == j && mat[i][j] != 1)) {
            return false;
        }
        // Recursively check the next element in the sub-matrix
        return isIdentity(mat, x, size, i, j + 1);
    }
    //////////////////////////////////////

    /**
     * Recursive function to find the maximum size of a square sub-matrix within a larger matrix that represents an identity matrix.
     * This exercise is from 2022 A moed 67.
     *
     * @param mat The matrix to analyze.
     * @return The maximum size of a square sub-matrix representing an identity matrix.
     */
    public static int maxMatrix(int[][] mat) {
        return maxMatrix(mat, 0, mat.length);
    }

    // Recursive helper function to find the maximum size of a square sub-matrix representing an identity matrix
    private static int maxMatrix(int[][] mat, int x, int size) {
        // Check if the sub-matrix at position x with size 'size' represents an identity matrix
        boolean isIdentity = isIdentity(mat, x, size);
        // If the sub-matrix is an identity matrix, return its size
        if (isIdentity) {
            return size;
        }
        // Otherwise, recursively decrease the size of the sub-matrix and check again
        int max = maxMatrix(mat, x + 1, size - 2);
        return max;
    }

    ////////////////////
    /**
     * Recursive function to print all possible arithmetic expressions using the elements of an array that evaluate to a given number.
     * This exercise is from 2021B moed 92.
     *
     * @param arr The array of integers to use in the expressions.
     * @param num The target number for the expressions to evaluate to.
     * @return The total number of valid expressions found.
     */
    public static int printExpr(int[] arr, int num) {
        return printExpr(arr, num, 0, "", 0);
    }

    // Recursive helper function to print arithmetic expressions
    private static int printExpr(int[] arr, int num, int i, String print, int sum) {
        // If the current sum equals the target number, print the expression
        if (sum == num) {
            System.out.println(print + " = " + num);
            return 1;
        }
        // If all elements of the array have been processed, or if the sum exceeds the target number and there are no more elements to consider, return 0
        if (i == arr.length || (sum > num && i == arr.length)) {
            return 0;
        }
        // Recursively explore three possibilities: adding the current element, subtracting it, or not using it
        int plus = printExpr(arr, num, i + 1, print + "+" + arr[i], sum + arr[i]);
        int minus = printExpr(arr, num, i + 1, print + "-" + arr[i], sum - arr[i]);
        int nothing = printExpr(arr, num, i + 1, print, sum);
        // Return the total count of valid expressions found
        return plus + minus + nothing;
    }

    //////////////////////////////////////
    //////////////////////////////////////
    /**
     * Recursive function to count the number of ways to make a sum of numbers using a given set of lengths.
     * This exercise is from 2020B moed 81.
     *
     * @param lengths An array representing the lengths of the numbers that can be used to make the sum.
     * @param k       The target sum to achieve.
     * @param num     The maximum number of elements that can be used.
     * @return The number of ways to make the sum using the given lengths.
     */
    public static int makeSum(int[] lengths, int k, int num) {
        return makeSum(lengths, k, num, 0);
    }

    // Recursive helper function to count the number of ways to make the sum
    private static int makeSum(int[] lengths, int k, int num, int i) {
        // If k or num become negative, or if all elements have been processed, return 0
        if (k < 0 || num < 0 || i > lengths.length - 1) {
            return 0;
        }
        // If k becomes 0, one way to make the sum is found
        if (k == 0) {
            return 1;
        }
        // Recursively explore two possibilities: taking the current element and staying at the same index, or moving to the next index without taking the current element
        int takeAndStay = makeSum(lengths, k - lengths[i], num - 1, i);
        int continueDontTake = makeSum(lengths, k, num, i + 1);
        // Return the sum of the counts from both possibilities
        return takeAndStay + continueDontTake;
    }

    /////////////////////////////
    //////////////////////
    //////////////////////
    /**
     * Recursive function to determine if an array can be split into three parts with equal sums.
     * This exercise is from 2021B moed 60.
     *
     * @param arr The array to split.
     * @return True if the array can be split into three parts with equal sums, otherwise false.
     */
    public static boolean split3(int[] arr) {
        return split3(arr, 0, 0, 0, 0);
    }

    // Recursive helper function to split the array into three parts with equal sums
    private static boolean split3(int[] arr, int i, int sum1, int sum2, int sum3) {
        // If all elements have been processed and sums are equal, return true
        if (i == arr.length && sum1 == sum2 && sum2 == sum3) {
            return true;
        }
        // If all elements have been processed but sums are not equal, return false
        if (i == arr.length) {
            return false;
        }
        // Recursively explore three options: adding the current element to sum1, sum2, or sum3
        boolean sum1Option = split3(arr, i + 1, sum1 + arr[i], sum2, sum3);
        boolean sum2Option = split3(arr, i + 1, sum1, sum2 + arr[i], sum3);
        boolean sum3Option = split3(arr, i + 1, sum1, sum2, sum3 + arr[i]);
        // Return true if any of the options leads to a successful split
        return sum1Option || sum2Option || sum3Option;
    }

    //////////////////////////////////////////////////////////////////
    /**
     * Recursive function to determine if an array can be split into two parts with equal sum and equal lengths.
     * This exercise is from 2021B moed 62.
     *
     * @param arr The array to split.
     * @return True if the array can be split into two parts with equal sum and equal lengths, otherwise false.
     */
    public static boolean equalSplit(int[] arr) {
        return equalSplit(arr, 0, 0, 0, 0, 0);
    }

    // Recursive helper function to split the array into two parts with equal sum and equal lengths
    private static boolean equalSplit(int[] arr, int i, int sum1, int sum2, int sum1Length, int sum2Length) {
        // If all elements have been processed and sums and lengths are equal, return true
        if (i == arr.length && sum1 == sum2 && sum1Length == sum2Length) {
            return true;
        }
        // If all elements have been processed but sums and lengths are not equal, return false
        if (i >= arr.length) {
            return false;
        }
        // Recursively explore two options: adding the current element to sum1 or sum2
        boolean sum1Path = equalSplit(arr, i + 1, sum1 + arr[i], sum2, sum1Length + 1, sum2Length);
        boolean sum2Path = equalSplit(arr, i + 1, sum1, sum2 + arr[i], sum1Length, sum2Length + 1);
        // Return true if any of the options leads to a successful split
        return sum1Path || sum2Path;
    }

    ///////////////////////////
    /////////////////////////////////////////////////////////////////
    /**
     * Recursive function to find the minimum price path in a 2D matrix.
     * This exercise is from 2021A moed 85 corona.
     *
     * @param mat The matrix representing the prices.
     * @return The minimum price to reach the last column from the first column.
     */
    public static int minPrice(int[][] mat) {
        return minPrice(mat, 0, 0);
    }

    // Recursive helper function to find the minimum price path
    private static int minPrice(int[][] mat, int i, int j) {
        // Base case: if we reach the last column, return the price at the current position
        if (j == mat[0].length - 1) {
            return mat[i][j];
        }
        // If we reach beyond the matrix boundaries or encounter a barrier (-1), return the maximum integer value
        if (i == mat.length || mat[i][j] == -1) {
            return Integer.MAX_VALUE;
        }
        // Calculate the minimum price path by considering the cost of moving down and the cost of moving left
        int pathDown = mat[i][j] + minPrice(mat, i + 1, j);
        int pathRight = minPrice(mat, i, j + 1);
        // Return the minimum cost of the two paths
        return Math.min(pathDown, pathRight);
    }

    //////////////////////////
    /**
     * Recursive function to find the maximum sum that a knight can achieve in a chessboard.
     * This exercise is from 2020B moed 98.
     *
     * @param mat The chessboard represented as a matrix.
     * @return The maximum sum achievable by a knight starting from the top-left corner.
     */
    public static int maxSumKnight(int[][] mat) {
        return maxSumKnight(mat, 0, 0, mat[0][0], 0);
    }

    // Recursive helper function to find the maximum sum achievable by a knight
    private static int maxSumKnight(int[][] mat, int i, int j, int prev, int sum) {
        // Base cases: If the knight moves out of the chessboard boundaries, encounters a barrier, or exceeds the previous value by more than 1, return -1
        if (i < 0 || j < 0 || j >= mat.length || i >= mat[0].length || Math.abs(prev - mat[i][j]) > 1 || mat[i][j] == -1) {
            return -1;
        }
        // If the knight reaches the bottom-right corner, return the sum of values encountered
        if (i == mat.length - 1 && j == mat[0].length - 1) {
            return sum + mat[i][j];
        }
        // Update the sum and previous value, mark the current position as visited
        sum += mat[i][j];
        prev = mat[i][j];
        mat[i][j] = -1;
        // Explore all possible moves of the knight and find the maximum sum achievable
        int path1 = maxSumKnight(mat, i + 2, j + 1, prev, sum);
        int path2 = maxSumKnight(mat, i + 2, j - 1, prev, sum);
        int path3 = maxSumKnight(mat, i - 2, j + 1, prev, sum);
        int path4 = maxSumKnight(mat, i - 2, j - 1, prev, sum);
        int path5 = maxSumKnight(mat, i - 1, j - 2, prev, sum);
        int path6 = maxSumKnight(mat, i - 1, j + 2, prev, sum);
        int path7 = maxSumKnight(mat, i + 1, j - 2, prev, sum);
        int path8 = maxSumKnight(mat, i + 1, j + 2, prev, sum);
        // Restore the original value at the current position
        mat[i][j] = prev;
        // Find the maximum of all paths and return it
        int max1 = Math.max(path1, path2);
        int max2 = Math.max(path3, path4);
        int max3 = Math.max(path5, path6);
        int max4 = Math.max(path7, path8);
        int maxOfMax1 = Math.max(max1, max2);
        int maxOfMax2 = Math.max(max3, max4);
        int biggestMax = Math.max(maxOfMax1, maxOfMax2);
        return biggestMax;
    }

    ////////////////////////////
    /**
     * Recursive function to find the total number of ways to reach the bottom-right corner of a matrix with limited turns.
     * This exercise is from 2020B moed 81.
     *
     * @param mat The matrix representing the path.
     * @param k   The maximum number of allowed turns.
     * @return The total number of ways to reach the bottom-right corner with the given number of turns.
     */
    public static int totalWays(int[][] mat, int k) {
        return totalWays(mat, k, 0, 0, 'x', 'x');
    }

    // Recursive helper function to find the total number of ways with limited turns
    private static int totalWays(int[][] mat, int k, int i, int j, char before, char current) {
        // Base case: If the indices are out of bounds or the turns are exhausted, return 0
        if (i > mat.length - 1 || j > mat[0].length - 1 || k < 0) {
            return 0;
        }
        // Decrement k if the current move changes direction
        if ((before == 'R' && current == 'D') || (before == 'D' && current == 'R')) {
            k--;
        }
        // Base case: If the bottom-right corner is reached and turns are not exceeded, return 1
        if (i == mat.length - 1 && j == mat[0].length - 1 && k == 0) {
            return 1;
        }
        // Recursive calls to explore moving right and moving down
        int right = totalWays(mat, k, i, j + 1, current, 'R');
        int down = totalWays(mat, k, i + 1, j, current, 'D');
        // Return the sum of ways from moving right and moving down
        return down + right;
    }

    /////////////////////////////////
    /**
     * Recursive function to find the maximum sum achievable by moving through a matrix with blocked cells.
     * This exercise is from 2020A moed 85.
     *
     * @param mat The matrix representing the grid with blocked cells denoted by -1.
     * @return The maximum sum achievable by moving through the matrix.
     */
    public static int findMaximum(int[][] mat) {
        if (mat.length == 0 || mat[0][0] == -1) {
            return -1;
        }
        return findMaximum(mat, 0, 0);
    }

    // Recursive helper function to find the maximum sum achievable by moving through the matrix
    private static int findMaximum(int[][] mat, int i, int j) {
        // Base case: If out of bounds or current cell is blocked, return 0
        if (j < 0 || i > mat.length - 1 || j > mat[0].length - 1 || mat[i][j] == -1) {
            return 0;
        }
        int left = 0, right = 0, down = 0;
        int temp = mat[i][j]; // Temporary hold mat[i][j] value
        mat[i][j] = -1; // Change to -1 to avoid infinite recursion
        if (i % 2 == 0) { // Even row
            right = temp + findMaximum(mat, i, j + 1); // Recursive call right, down path + mat[i][j]
            down = temp + findMaximum(mat, i + 1, j);
        } else { // Odd row
            left = temp + findMaximum(mat, i, j - 1); // Recursive call left, down + mat[i][j]
            down = temp + findMaximum(mat, i + 1, j);
        }
        mat[i][j] = temp; // Return to the original value
        return Math.max(down, Math.max(left, right)); // Return the maximum path
    }

    ////////////////////////////////////////////
    /*
     * Note: This code uses an external class 'Item', which is not provided here.
     *       The 'Item' class should have methods 'getValue()' and 'getWeight()' to retrieve
     *       the value and weight of an item respectively.
     *
     * moed 2017B moed 90
     *
     * This method implements the knapsack problem using recursion.
     * It finds the maximum value that can be obtained by selecting items
     * from the given array 'items' such that the total weight does not exceed 'w'.
     *
     * @param items An array of 'Item' objects representing the available items.
     * @param w The maximum weight capacity of the knapsack.
     * @return The maximum value that can be obtained by selecting items within the weight limit.
     */
    /*public static int knapSack(Item[] items, int w) {
    return knapSack(items, w, 0);
    }

    // Recursive helper method for the knapsack problem
    private static int knapSack(Item[] items, int w, int i) {
    // Base case: If the weight limit is exceeded, return a large negative value
    if (w < 0) {
    return Integer.MIN_VALUE;
    }
    // Base case: If all items are processed or the weight limit is 0, return 0
    if (i > items.length - 1 || w == 0) {
    return 0;
    }
    // Recursively explore two options: taking the current item or not taking it
    int takeAndContinue = items[i].getValue() + knapSack(items, w - items[i].getWeight(), i + 1);
    int dontTakeAndContinue = knapSack(items, w, i + 1);
    // Return the maximum value obtained from the two options
    return Math.max(takeAndContinue, dontTakeAndContinue);
    }
     */

    //////////////////////
    /**
     * moed 2017B moed 84
     *
     * This method calculates the number of ways to reach a target 'n' by making 'k' moves.
     * Each move can either increment or decrement the current value by 1.
     *
     * @param k The number of moves remaining.
     * @param n The target value to reach.
     * @return The number of ways to reach the target value within the given number of moves.
     */
    public static int ways(int k, int n) {
        // Base case: If no moves are left and the target value is reached, return 1
        if (k == 0 && n == 0) {
            return 1;
        }
        // Base case: If the number of moves is negative, return 0
        if (k < 0) {
            return 0;
        }
        // Recursively explore two options: moving left (incrementing) or right (decrementing)
        int leftPath = ways(k - 1, n + 1);  // Move left: Increment the current value
        int rightPath = ways(k - 1, n - 1); // Move right: Decrement the current value
        // Return the sum of ways obtained from moving left and right
        return leftPath + rightPath;
    }

    ///////////////////
    /**
     * moed 2017B moed 84
     *
     * This method calculates the minimum number of steps required to reach the target 'n'
     * by adding 1, 5, or 7 to the current sum in each step.
     *
     * @param n The target sum to reach.
     * @return The minimum number of steps required to reach the target sum.
     */
    public static int oneFiveSeven(int n) {
        return oneFiveSeven(n, 0);
    }

    // Recursive helper method to calculate the minimum number of steps
    private static int oneFiveSeven(int n, int sum) {
        // Base case: If the current sum equals the target, return 0 (no additional steps needed)
        if (sum == n) {
            return 0;
        }
        // Base case: If the current sum exceeds the target, return a large value
        if (sum > n) {
            return Integer.MAX_VALUE - sum; // Subtract sum from max value to avoid integer overflow
        }
        // Recursively explore three options: adding 1, 5, or 7 to the current sum
        int one = 1 + oneFiveSeven(n, sum + 1);
        int five = 1 + oneFiveSeven(n, sum + 5);
        int seven = 1 + oneFiveSeven(n, sum + 7);
        // Return the minimum number of steps among the three options
        return Math.min(Math.min(one, five), seven);
    }

    /////////////////////////////////////////
    /*
     * moed 2016A moed 87
     *
     * This method calculates the minimum difference between the sums of two subsets
     * obtained by partitioning the given array 'arr' into two subsets.
     *
     * @param arr The input array of integers.
     * @return The minimum absolute difference between the sums of two subsets.
     */
    public static int midDiff(int[] arr) {
        return midDiff(arr, 0, 0, 0);
    }

    // Recursive helper method to calculate the minimum difference between subset sums
    private static int midDiff(int[] arr, int i, int sum1, int sum2) {
        // Base case: If all elements are processed, return the absolute difference between the sums
        if (i == arr.length) {
            return Math.abs(sum1 - sum2);
        }
        // Recursively explore two options: including the current element in subset 1 or subset 2
        int option1 = midDiff(arr, i + 1, sum1 + arr[i], sum2);
        int option2 = midDiff(arr, i + 1, sum1, sum2 + arr[i]);
        // Return the minimum absolute difference between subset sums
        return Math.min(option1, option2);
    }

    ////////////////////////////////////
    /*
     * moed 2016A moed 92
     *
     * This method calculates the minimum number of steps for a knight piece on a chessboard
     * to reach a cell with the symbol 'K', starting from the cell at coordinates (i, j).
     *
     * @param minChess The chessboard represented as a 2D array of characters.
     * @param i        The row index of the starting cell.
     * @param j        The column index of the starting cell.
     * @return         The minimum number of steps required for the knight to reach the 'K' cell,
     *                 or -1 if it's impossible to reach the 'K' cell from the starting cell.
     */
    public static int minPath(char[][] minChess, int i, int j) {
        int min = minPath(minChess, i, j, 0);
        // If the minimum path is not found (equal to Integer.MAX_VALUE), return -1
        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }

    // Recursive helper method to calculate the minimum number of steps for the knight
    private static int minPath(char[][] minChess, int i, int j, int sum) {
        // Base case: If the knight goes out of bounds or encounters a visited cell or the target cell,
        // return the maximum integer value to indicate that it's not a valid path
        if (i < 0 || j < 0 || i >= minChess.length || j >= minChess[0].length || minChess[i][j] == 'Z') {
            return Integer.MAX_VALUE;
        }
        // Base case: If the knight reaches the 'K' cell, return the current sum (number of steps)
        if (minChess[i][j] == 'K') {
            return sum;
        }
        // Mark the current cell as visited
        minChess[i][j] = 'Z';
        // Explore all possible moves of the knight and calculate the minimum path
        int opt1 = minPath(minChess, i + 2, j + 1, sum + 1);
        int opt2 = minPath(minChess, i + 2, j - 1, sum + 1);
        int opt3 = minPath(minChess, i - 2, j + 1, sum + 1);
        int opt4 = minPath(minChess, i - 2, j - 1, sum + 1);
        int opt5 = minPath(minChess, i - 1, j + 2, sum + 1);
        int opt6 = minPath(minChess, i - 1, j - 2, sum + 1);
        int opt7 = minPath(minChess, i + 1, j + 2, sum + 1);
        int opt8 = minPath(minChess, i + 1, j - 2, sum + 1);
        // Restore the current cell to its original state
        minChess[i][j] = '0';
        // Find the minimum path among all possible moves
        int minPath = Math.min(Math.min(opt1, opt2), Math.min(opt3, opt4));
        int minPath2 = Math.min(Math.min(opt5, opt6), Math.min(opt7, opt8));
        int min = Math.min(minPath, minPath2);
        return min;
    }

    /////////////////////////
    /*
     * moed 2016A moed 83
     *
     * This method calculates the minimum number of points needed to traverse a matrix 'm'
     * from the top-left corner to the bottom-right corner, allowing movements only to the right and down.
     * Each cell of the matrix contributes points to the total, and if the total becomes negative,
     * the method ensures that enough points are accumulated to keep the total non-negative.
     *
     * @param m The input matrix of integers.
     * @return The minimum number of points needed to traverse the matrix.
     */
    public static int minPoints(int[][] m) {
        return minPoints(m, 0, 0, 0, 0);
    }

    // Recursive helper method to calculate the minimum number of points needed
    private static int minPoints(int[][] m, int i, int j, int number, int sum) {
        // Base case: If the bottom-right corner is reached, return the accumulated number of points
        if ((i == m.length && j == m[0].length - 1) || (i == m.length - 1 && j == m[0].length)) {
            return number;
        }
        // Base case: If out of bounds, return the maximum integer value
        if (i == m.length || j == m[0].length) {
            return Integer.MAX_VALUE;
        }
        // Add the value of the current cell to the sum
        sum += m[i][j];
        // Ensure that the sum plus the accumulated number of points is non-negative
        if (sum + number < 0) {
            number = Math.abs(sum) + 1;
        }
        // If the sum plus the accumulated number of points is zero, increment the number of points by one
        if (sum + number == 0) {
            number += 1;
        }
        // Recursively explore two options: moving down and moving right
        int leftAdd = minPoints(m, i + 1, j, number, sum);
        int downAdd = minPoints(m, i, j + 1, number, sum);
        // Return the minimum number of points needed among the two options
        return Math.min(leftAdd, downAdd);
    }

}
 