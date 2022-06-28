package com.yun.code.package1200.package70;

/**
 * A 和 B 在一个 3 x 3 的网格上玩井字棋。
 *
 * 井字棋游戏的规则如下：
 *
 * 玩家轮流将棋子放在空方格 (" ") 上。
 * 第一个玩家 A 总是用 "X" 作为棋子，而第二个玩家 B 总是用 "O" 作为棋子。
 * "X" 和 "O" 只能放在空方格中，而不能放在已经被占用的方格上。
 * 只要有 3 个相同的（非空）棋子排成一条直线（行、列、对角线）时，游戏结束。
 * 如果所有方块都放满棋子（不为空），游戏也会结束。
 * 游戏结束后，棋子无法再进行任何移动。
 * 给你一个数组 moves，其中每个元素是大小为 2 的另一个数组（元素分别对应网格的行和列），它按照 A 和 B 的行动顺序（先 A 后 B）记录了两人各自的棋子位置。
 *
 * 如果游戏存在获胜者（A 或 B），就返回该游戏的获胜者；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
 *
 * 你可以假设 moves 都 有效（遵循井字棋规则），网格最初是空的，A 将先行动。
 */
public class S1275找出井字棋的获胜者 {

    private static int[][] arr = new int[8][3];
    static {
        arr[0]=new int[]{0,1,2};
        arr[1]=new int[]{3,4,5};
        arr[2]=new int[]{6,7,8};
        arr[3]=new int[]{0,3,6};
        arr[4]=new int[]{1,4,7};
        arr[5]=new int[]{2,5,8};
        arr[6]=new int[]{0,4,8};
        arr[7]=new int[]{2,4,6};
    }

    public static void main(String[] args) {

    }

    public String tictactoe(int[][] moves) {
        //3步时候判断A获胜或者B获胜

        //4步时候判断A获胜或者B获胜

        //5步时候判断A获胜

        //平局
        return "";
    }
}
