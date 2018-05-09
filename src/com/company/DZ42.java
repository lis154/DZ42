package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class DZ42 {

    public static  char[][]map;
    public static final int SIZE = 7;
    public static final int DOTS_TO_WIN = 5;
    public static final int DOTS_TO_CLOSE_LINE = DOTS_TO_WIN-2;
    public static final char DOT_EMPTY = '.';
    public static final char DOT_X = 'X';
    public static final char DOT_0 = '0';
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();
   // public static ArrayList<Integer> vozvr = new ArrayList<>();
    public static int[][] priorty = new int[SIZE][SIZE];

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
            System.out.println(find_gamer_priority(DOT_X, DOTS_TO_CLOSE_LINE));
            System.out.println(find_gamer_priority(DOT_X, DOTS_TO_CLOSE_LINE+1));
            sravnenie_matrix(); // сравниваем матрицы, если в на точка, обнуляем приоритет
            pechat_priority();
            System.out.println(findMaxEl());
            computerTurn2();
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
            sravnenie_matrix(); // сравниваем матрицы, если в основной не точка, обнуляем приоритет в матрице приоритетов
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

     //проверка квадратов по диагонали + сократить

        for (int k = 0; k<= SIZE-DOTS_TO_WIN; k++ ) {
            for (int i = 0; i <= SIZE - DOTS_TO_WIN; i++) {
                //System.out.println("i =   " + i);
                if (DOTS_TO_WIN + i <= SIZE) {
                    for (int j = k, t = i; j < DOTS_TO_WIN+k; j++, t++) {
                        if (map[j][t] == synb) summ++;
                        else summ = 0;
                        if (summ == DOTS_TO_WIN) {
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
                        if (summ == DOTS_TO_WIN) {
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


    public static void computerTurn2 () // ход компьютера
    {
        int x, y;
        int[] index = findIndexMaxElement(findMaxEl());
        System.out.println(index[0] + "   " + index[1]);
        if (findMaxEl() == 0)
        {

            do {
                x = rand.nextInt(SIZE);
                y = rand.nextInt(SIZE);

            }while (!isCEllValid(x,y));
            System.out.println("Компьютер сходил: x=" + (x+1) + " y=" + (y+1));
            map[y][x] = DOT_0;

        }
        else
        {
            System.out.println("Компьютер сходил: x=" + index[0] + " y=" + index[1]);
            map[index[0]][index[1]] = DOT_0;
        }
    }

    public static ArrayList<Integer> find_gamer_priority(char synb, int proverk) // заполняем массив с приоритетами
    {
        int razmer_line = proverk;
        ArrayList<Integer> vozvr = new ArrayList<>();
        int summ = 0;
        for (int i = 0; i < SIZE; i++) {  // по горизонтали
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == synb)
                    summ++; else summ = 0;
                if (summ == razmer_line)
                {
                    if (j + 1 < SIZE )
                        priorty[i][j+1] = 1000+razmer_line;
                    if (j-razmer_line >= 0)
                        priorty[i][j-razmer_line] = 1000+razmer_line;
                }
            }
            summ = 0;
        }
        for (int i = 0; i < SIZE; i++) { // по вертикали (передалить в 1 )
            for (int j = 0; j < SIZE; j++) {
                if (map[j][i] == synb)
                    summ++; else summ = 0;
                if (summ == razmer_line)
                {
                    if (j + 1 < SIZE )
                        priorty[j+1][i] = 1000+razmer_line;
                    if (j-razmer_line >= 0)
                        priorty[j-razmer_line][i] = 1000+razmer_line;
                }
            }
            summ = 0;
        }
        //проверка квадратов по диагонали + сократить
        for (int k = 0; k<= SIZE-razmer_line; k++ ) {
            for (int i = 0; i <= SIZE - razmer_line; i++) {
                //System.out.println("i =   " + i);
                if (razmer_line + i <= SIZE) {
                    for (int j = k, t = i; j < razmer_line+k; j++, t++) {
                        if (map[j][t] == synb) summ++;
                        else summ = 0;
                        if (summ == razmer_line) {
                            if (j + 1 < SIZE && t+1 < SIZE)
                                priorty[j+1][t+1] = 1000+razmer_line;
                            if (j-razmer_line >= 0 && t-razmer_line >= 0)
                                priorty[j-razmer_line][t-razmer_line] = 1000+razmer_line;

                        }
                    }
                }
                summ = 0;
            }
        }
        for (int k = 0; k<= SIZE-razmer_line; k++ ) {
            for (int i = 0; i <= SIZE - razmer_line; i++) {
                if (razmer_line + i <= SIZE) {
                    for (int j = razmer_line+k-1, t = i; j >= k; j--, t++) {
                        if (map[j][t] == synb) summ++;
                        else summ = 0;
                        if (summ == razmer_line) {
                            if (j + 1 < SIZE && t+1 < SIZE)
                                priorty[j-1][t+1] = 1000+razmer_line;
                            System.out.println("j = " + j + "t = " + t);
                            if (j+razmer_line >= 0 && t-razmer_line >= 0)
                                priorty[j+razmer_line][t-razmer_line] = 1000+razmer_line;
                        }
                    }
                }
                summ = 0;
            }
        }
        return vozvr;
    }

    public static void pechat_priority()
    {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + priorty[i][j]);
            }
            System.out.println("");
        }
    }

    public static boolean inPole(int x, int y) // проверка вхождения в поле
    {
        if (x < 0 || x >= SIZE || y < 0 || y >+ SIZE) return false;
        return true;
    }

    public static void sravnenie_matrix() // сравниваем матрицы, если не точка то обнуляем приоритет
    {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] != DOT_EMPTY)
                {
                    priorty[i][j] = 0;
                }
            }
        }
    }

    public static int findMaxEl() { // находим максимальны приоритет
        int max = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (max < priorty[i][j]) max = priorty[i][j];
            }
        }
        return max;
    }

    public static int[] findIndexMaxElement (int element) // находим индекс максимального приоритета
    {
        int[] index = new int[2];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (priorty[i][j] == element ) {
                    index[0] = i;
                    index[1] = j;
                    System.out.println("element = " + element + "  i = " + i + "  j = " + j);
                    return index;
                }
            }
            }
        return null;
    }
}
