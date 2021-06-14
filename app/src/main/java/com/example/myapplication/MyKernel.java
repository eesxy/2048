package com.example.myapplication;

import java.util.LinkedList;

public class MyKernel implements Kernel {
    //分数
    private int point;
    //存储body中的16个数
    private final int[][] content;

    //构造函数
    public MyKernel() {
        point = 0;
        content = new int[4][4];
        //初值为0
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                content[i][j] = 0;
            }
        }
        //随机初始化两个数
        int loc = randomLocation();
        content[loc / 4][loc % 4] = randomNum();
        loc = randomLocation();
        content[loc / 4][loc % 4] = randomNum();
    }

    //向左移动
    @Override
    public void left() {
        int[][] nextState = new int[4][4];
        for (int i = 0; i < 4; ++i) {
            LinkedList<Integer> source = new LinkedList<>();
            for (int j = 0; j < 4; ++j) {
                if (content[i][j] != 0) {
                    source.add(content[i][j]);
                }
            }
            LinkedList<Integer> dest = move(source);
            int len = dest.size();
            for (int j = 0; j < len; ++j) {
                nextState[i][j] = dest.get(j);
            }
            for (int j = len; j < 4; ++j) {
                nextState[i][j] = 0;
            }
        }
        //判断是否能够向左移动
        boolean canMove = false;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (nextState[i][j] != content[i][j]) {
                    canMove = true;
                    content[i][j] = nextState[i][j];
                }
            }
        }
        if (canMove) {
            int loc = randomLocation();
            content[loc / 4][loc % 4] = randomNum();
        }
    }

    //向右移动
    @Override
    public void right() {
        int[][] nextState = new int[4][4];
        for (int i = 0; i < 4; ++i) {
            LinkedList<Integer> source = new LinkedList<>();
            for (int j = 0; j < 4; ++j) {
                if (content[i][3 - j] != 0) {
                    source.add(content[i][3 - j]);
                }
            }
            LinkedList<Integer> dest = move(source);
            int len = dest.size();
            for (int j = 0; j < len; ++j) {
                nextState[i][3 - j] = dest.get(j);
            }
            for (int j = len; j < 4; ++j) {
                nextState[i][3 - j] = 0;
            }
        }
        boolean canMove = false;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (nextState[i][j] != content[i][j]) {
                    canMove = true;
                    content[i][j] = nextState[i][j];
                }
            }
        }
        if (canMove) {
            int loc = randomLocation();
            content[loc / 4][loc % 4] = randomNum();
        }
    }

    //向上移动
    @Override
    public void up() {
        int[][] nextState = new int[4][4];
        for (int i = 0; i < 4; ++i) {
            LinkedList<Integer> source = new LinkedList<>();
            for (int j = 0; j < 4; ++j) {
                if (content[j][i] != 0) {
                    source.add(content[j][i]);
                }
            }
            LinkedList<Integer> dest = move(source);
            int len = dest.size();
            for (int j = 0; j < len; ++j) {
                nextState[j][i] = dest.get(j);
            }
            for (int j = len; j < 4; ++j) {
                nextState[j][i] = 0;
            }
        }
        boolean canMove = false;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (nextState[i][j] != content[i][j]) {
                    canMove = true;
                    content[i][j] = nextState[i][j];
                }
            }
        }
        if (canMove) {
            int loc = randomLocation();
            content[loc / 4][loc % 4] = randomNum();
        }
    }

    //向下移动
    @Override
    public void down() {
        int[][] nextState = new int[4][4];
        for (int i = 0; i < 4; ++i) {
            LinkedList<Integer> source = new LinkedList<>();
            for (int j = 0; j < 4; ++j) {
                if (content[3 - j][i] != 0) {
                    source.add(content[3 - j][i]);
                }
            }
            LinkedList<Integer> dest = move(source);
            int len = dest.size();
            for (int j = 0; j < len; ++j) {
                nextState[3 - j][i] = dest.get(j);
            }
            for (int j = len; j < 4; ++j) {
                nextState[3 - j][i] = 0;
            }
        }
        boolean canMove = false;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (nextState[i][j] != content[i][j]) {
                    canMove = true;
                    content[i][j] = nextState[i][j];
                }
            }
        }
        if (canMove) {
            int loc = randomLocation();
            content[loc / 4][loc % 4] = randomNum();
        }
    }

    //判断游戏是否结束
    @Override
    public boolean isEnd() {
        //判断是否有空余位置
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (content[i][j] == 0)
                    return false;
            }
        }
        //判断是否有相邻位置的值相同
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (content[i][j] == content[i][j + 1])
                    return false;
                if (content[j][i] == content[j + 1][i])
                    return false;
            }
        }
        return true;
    }

    //返回一个新的面板
    @Override
    public int[][] board() {
        int[][] myBoard = new int[4][4];
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                myBoard[i][j] = content[i][j];
            }
        }
        return myBoard;
    }

    //返回当前分数
    @Override
    public int score() {
        return point;
    }

    //生成一个随机的坐标
    public int randomLocation() {
        LinkedList<Integer> empty = new LinkedList<>();
        for (int i = 0; i < 16; ++i) {
            if (content[i / 4][i % 4] == 0) {
                empty.add(i);
            }
        }
        int len = empty.size();
        int ram = (int) (Math.random() * (len - 1));
        return empty.get(ram);
    }

    //生成一个随机数
    public int randomNum() {
        int ram = (int) (Math.random() * 99);
        if (ram < 70)
            return 2;
        return 4;
    }

    //用原向量生成一个新的向量
    public LinkedList<Integer> move(LinkedList<Integer> source) {
        LinkedList<Integer> dest = new LinkedList<>();
        while (!source.isEmpty()) {
            if (source.size() >= 2 && source.get(0).equals(source.get(1))) {
                point += 2 * source.get(0);
                dest.add(2 * source.get(0));
                source.remove(0);
            } else {
                dest.add(source.get(0));
            }
            source.remove(0);
        }
        return dest;
    }
}
