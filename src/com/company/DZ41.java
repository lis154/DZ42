package com.company;

import java.util.Random;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class DZ41 {

    public static  char[][]map;
    public static final int SIZE = 6;
    public static final int DOTS_TO_WIN = 4;
    public static final char DOT_EMPTY = '.';
    public static final char DOT_X = 'X';
    public static final char DOT_0 = '0';
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        initMap();
        printMAP();
        while (true)
        {
            humanTurn();
            printMAP();
            if (checkWin2(DOT_X))
            {
                System.out.println("Победил игрок");
                break;
            }
            if (isMapFull())
            {
                System.out.println("Ничья");
                break;
            }
            // printMAP();
            computerTurn();
            printMAP();
            if (checkWin2(DOT_0))
            {
                System.out.println("Победил компьютер");
                break;
            }
            if (isMapFull())
            {
                System.out.println("Ничья");
                break;
            }
            // printMAP();
        }
        printMAP();
        System.out.println("GAME OVER");
    }

    public static void initMap()
    {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMAP()
    {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println("");
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i+1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static boolean isCEllValid(int x, int y)
    {
        if (x < 0 || x >= SIZE || y < 0 || y >+ SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    public static void humanTurn()
    {
        int x, y;
        do {

            System.out.println("Введите свой ход X and Y");
            x = sc.nextInt()-1;
            y = sc.nextInt()-1;
        } while (!isCEllValid(x,y));
        map[y][x] = DOT_X;
    }

    public static void computerTurn()
    {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        }while (!isCEllValid(x,y));
        System.out.println("Компьютер сходил: x=" + (x+1) + " y=" + (y+1));
        map[y][x] = DOT_0;
    }

    public static boolean checkWin2(char synb)
    {
        int summ = 0;
        for (int i = 0; i < SIZE; i++) {  // по горизонтали
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == synb)
                    summ++; else summ = 0;
                if (summ == DOTS_TO_WIN)
                {
                    System.out.println("1");
                    return true;
                }
            }
            summ = 0;
        }


        for (int i = 0; i < SIZE; i++) { // по вертикали (передалить в 1 )
            for (int j = 0; j < SIZE; j++) {
                if (map[j][i] == synb)
                    summ++; else summ = 0;
                if (summ == DOTS_TO_WIN)
                {
                    System.out.println("1");
                    return true;
                }
            }
            summ = 0;
        }

     //проверка квадратов по диагонали

        for (int k = 0; k<= SIZE-DOTS_TO_WIN; k++ ) {
            for (int i = 0; i <= SIZE - DOTS_TO_WIN; i++) {
                //System.out.println("i =   " + i);
                if (DOTS_TO_WIN + i <= SIZE) {
                    for (int j = k, t = i; j < DOTS_TO_WIN+k; j++, t++) {
                        if (map[j][t] == synb) summ++;
                        else summ = 0;
                        if (summ == 4) {
                            return true;
                        }
                    }
                }
                summ = 0;
            }
        }

        for (int k = 0; k<= SIZE-DOTS_TO_WIN; k++ ) {
            for (int i = 0; i <= SIZE - DOTS_TO_WIN; i++) {
                if (DOTS_TO_WIN + i <= SIZE) {
                    for (int j = DOTS_TO_WIN+k-1, t = i; j >= k; j--, t++) {
                        if (map[j][t] == synb) summ++;
                        else summ = 0;
                        if (summ == 4) {
                            return true;
                        }
                    }
                }
                summ = 0;
            }
        }


        return false;
    }

    public static boolean isMapFull()
    {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

}
