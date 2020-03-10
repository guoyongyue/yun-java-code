package com.yun.code.day07;

public class S63不同路径 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0]==1){
            return 0;
        }else {
            obstacleGrid[0][0]=1;
        }

        for (int i=1;i<obstacleGrid[0].length;i++){
            if(obstacleGrid[0][i]==1){
                obstacleGrid[0][i]=0;
            }else {
                if(obstacleGrid[0][i-1]==0){
                    obstacleGrid[0][i]=0;
                }else {
                    obstacleGrid[0][i]=1;
                }
            }
        }

        for (int i=1;i<obstacleGrid.length;i++){
            if(obstacleGrid[i][0]==1){
                obstacleGrid[i][0]=0;
            }else {
                if(obstacleGrid[i-1][0]==0){
                    obstacleGrid[i][0]=0;
                }else {
                    obstacleGrid[i][0]=1;
                }
            }

            obstacleGrid[i][0] = ( (obstacleGrid[i][0]==0 && obstacleGrid[i-1][0]==0) ? 0:1);
        }

        for (int i=1;i<obstacleGrid[0].length;i++) {
            for (int j = 1; j < obstacleGrid.length; j++) {

            }
        }


        return 0;
    }
}
