package com.example.myapplication;

import java.util.Vector;

public class MyKernel implements Kernel{
    //分数
    int point;
    //存储body中的16个数
    int [][] content;
    //构造函数
    MyKernel()
    {
        point=0;
        content=new int [4][4];
        //初值为0
        for(int i=0;i<4;++i)
        {
            for(int j=0;j<4;++j)
            {
                content[i][j]=0;
            }
        }
        //随机初始化两个数
        int loc=randomLocation();
        content[loc/4][loc%4]=randomNum();
        loc=randomLocation();
        content[loc/4][loc%4]=randomNum();
    }
    //向左移动
    @Override
    public void left() {
        int [][]nextState=new int [4][4];
        for(int i=0;i<4;++i)
        {
            Vector source=new Vector();
            for(int j=0;j<4;++j)
            {
                if(content[i][j]!=0)
                {
                    source.addElement(content[i][j]);
                }
            }
            Vector dest=move(source);
            int len=dest.size();
            for(int j=0;j<len;++j)
            {
                nextState[i][j]=(int)dest.elementAt(j);
            }
            for(int j=len;j<4;++j)
            {
                nextState[i][j]=0;
            }
        }
        //判断是否能够向左移动
        boolean canMove=false;
        for(int i=0;i<4;++i)
        {
            for(int j=0;j<4;++j)
            {
                if(nextState[i][j]!=content[i][j])
                {
                    canMove=true;
                    content[i][j]=nextState[i][j];
                }
            }
        }
        if(canMove==true) {
            int loc = randomLocation();
            content[loc / 4][loc % 4] = randomNum();
        }
    }
    //向右移动
    @Override
    public void right() {
        int [][]nextState=new int [4][4];
        for(int i=0;i<4;++i)
        {
            Vector source=new Vector();
            for(int j=0;j<4;++j)
            {
                if(content[i][3-j]!=0)
                {
                    source.addElement(content[i][3-j]);
                }
            }
            Vector dest=move(source);
            int len=dest.size();
            for(int j=0;j<len;++j)
            {
                nextState[i][3-j]=(int)dest.elementAt(j);
            }
            for(int j=len;j<4;++j)
            {
                nextState[i][3-j]=0;
            }
        }
        boolean canMove=false;
        for(int i=0;i<4;++i)
        {
            for(int j=0;j<4;++j)
            {
                if(nextState[i][j]!=content[i][j])
                {
                    canMove=true;
                    content[i][j]=nextState[i][j];
                }
            }
        }
        if(canMove==true) {
            int loc = randomLocation();
            content[loc / 4][loc % 4] = randomNum();
        }
    }
    //向上移动
    @Override
    public void up() {
        int [][]nextState=new int [4][4];
        for(int i=0;i<4;++i)
        {
            Vector source=new Vector();
            for(int j=0;j<4;++j)
            {
                if(content[j][i]!=0)
                {
                    source.addElement(content[j][i]);
                }
            }
            Vector dest=move(source);
            int len=dest.size();
            for(int j=0;j<len;++j)
            {
                nextState[j][i]=(int)dest.elementAt(j);
            }
            for(int j=len;j<4;++j)
            {
                nextState[j][i]=0;
            }
        }
        boolean canMove=false;
        for(int i=0;i<4;++i)
        {
            for(int j=0;j<4;++j)
            {
                if(nextState[i][j]!=content[i][j])
                {
                    canMove=true;
                    content[i][j]=nextState[i][j];
                }
            }
        }
        if(canMove==true) {
            int loc = randomLocation();
            content[loc / 4][loc % 4] = randomNum();
        }
    }
    //向下移动
    @Override
    public void down() {
        int [][]nextState=new int [4][4];
        for(int i=0;i<4;++i)
        {
            Vector source=new Vector();
            for(int j=0;j<4;++j)
            {
                if(content[3-j][i]!=0)
                {
                    source.addElement(content[3-j][i]);
                }
            }
            Vector dest=move(source);
            int len=dest.size();
            for(int j=0;j<len;++j)
            {
                nextState[3-j][i]=(int)dest.elementAt(j);
            }
            for(int j=len;j<4;++j)
            {
                nextState[3-j][i]=0;
            }
        }
        boolean canMove=false;
        for(int i=0;i<4;++i)
        {
            for(int j=0;j<4;++j)
            {
                if(nextState[i][j]!=content[i][j])
                {
                    canMove=true;
                    content[i][j]=nextState[i][j];
                }
            }
        }
        if(canMove==true) {
            int loc = randomLocation();
            content[loc / 4][loc % 4] = randomNum();
        }
    }
    //判断游戏是否结束
    @Override
    public boolean isEnd()
    {
        //判断是否有空余位置
        for(int i=0;i<4;++i)
        {
            for(int j=0;j<4;++j)
            {
                if(content[i][j]==0)
                    return false;
            }
        }
        //判断是否有相邻位置的值相同
        for(int i=0;i<4;++i)
        {
            for(int j=0;j<3;++j)
            {
                if(content[i][j]==content[i][j+1])
                    return false;
                if(content[j][i]==content[j+1][i])
                    return false;
            }
        }
        return true;
    }
    //返回一个新的面板
    @Override
    public int[][] board() {
        int [][] myBoard=new int [4][4];
        for(int i=0;i<4;++i)
        {
            for(int j=0;j<4;++j)
            {
                myBoard[i][j]=content[i][j];
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
    public int randomLocation()
    {
        Vector empty=new Vector();
        for(int i=0;i<16;++i)
        {
            if(content[i/4][i%4]==0)
            {
                empty.addElement(i);
            }
        }
        int len=empty.size();
        int ram=(int)(Math.random()*(len-1));
        return (int)empty.elementAt(ram);
    }
    //生成一个随机数
    public int randomNum()
    {
        int ram=(int) (Math.random()*99);
        if(ram<70)
            return 2;
        return 4;
    }
    //用原向量生成一个新的向量
    public Vector move(Vector source)
    {
        Vector dest=new Vector();
        while(!source.isEmpty())
        {
            if(source.size()>=2&&(int)source.elementAt(0)==(int)source.elementAt(1))
            {
                point+=2*(int)source.elementAt(0);
                dest.addElement(2*(int)source.elementAt(0));
                source.removeElementAt(0);
                source.removeElementAt(0);
            }
            else
            {
                dest.addElement(source.elementAt(0));
                source.removeElementAt(0);
            }
        }
        return dest;
    }
}
