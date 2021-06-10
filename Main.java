package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean gameWon;
        boolean draw = false;
        char currentPlayer = 'O';
        int[] newUserMove;
        char[][] inputArr = initializeEmptyInputArray();
        printBoard(inputArr);

        do {
            System.out.println(currentPlayer);
            currentPlayer = changePlayer(currentPlayer);
            System.out.println(currentPlayer);
            newUserMove = getUserMove(inputArr);
            addUserMoveToInputArr(inputArr, newUserMove, currentPlayer);
            printBoard(inputArr);
            draw = checkIfDraw(inputArr, draw);
            gameWon = checkIfGameWon(inputArr, currentPlayer);
            if (!gameWon && draw) {
                System.out.println("Draw");
            } else if (gameWon) {
                System.out.println(currentPlayer + " wins");
            }
        } while (!gameWon);
    }

    public static char changePlayer(char currentPlayer) {
         return (currentPlayer == 'O') ? 'X' : 'O';
    }

    private static char[][] initializeEmptyInputArray() {
        char[][] inputArr = new char[3][3];
        for (char[] row: inputArr) {
            Arrays.fill(row, ' ');
        }
        return inputArr;
    }

    private static void printBoard(char[][] inputArr) {
        System.out.println();
        System.out.println("---------");
        System.out.printf("| %c %c %c |\n", inputArr[0][0], inputArr[0][1], inputArr[0][2]);
        System.out.printf("| %c %c %c |\n", inputArr[1][0], inputArr[1][1], inputArr[1][2]);
        System.out.printf("| %c %c %c |\n", inputArr[2][0], inputArr[2][1], inputArr[2][2]);
        System.out.println("---------");
    }

    private static boolean checkIfDraw(char[][] inputArr, boolean draw) {
        int xCount = 0;
        int oCount = 0;
        // add up current moves and make sure there aren't to many X's or O's
        for (char[] row : inputArr) {
            for (char move : row) {
                if (move == 'X') {
                    xCount++;
                } else if (move == 'O') {
                    oCount++;
                }
            }
        }
//        if (Math.abs(oCount - xCount) > 1) {
//            System.out.println("Impossible");
//        }
//        boolean xWon = checkIfGameWon(inputArr, 'X');
//        boolean oWon = checkIfGameWon(inputArr, 'O');
////        if (xWon && oWon) {
////            System.out.println("Impossible");
////        }
//        if (xWon) {
//            System.out.println("X wins");
//            gameOver = true;
//        }
//        if (oWon) {
//            System.out.println("O wins");
//            gameOver = true;
//        }
        if (oCount + xCount == 9) {
            draw = true;
        }
        return draw;
    }

    private static boolean checkIfGameWon(char[][] inputArr, char player) {
        // check horizontal winner
        for (char[] row : inputArr) {
            if (row[0] == player && row[0] == row[1] && row[0] == row[2]) {
                return true;
            }
        }
        // check vertical winner
        for (int i = 0; i < 3; i++) {
            if (inputArr[0][i] == player && inputArr[0][i] == inputArr[1][i] && inputArr[0][i] == inputArr[2][i]) {
                return true;
            }
        }
        // check diagonal winners
        if ((inputArr[0][0] == player && inputArr[0][0] == inputArr[1][1] && inputArr[1][1] == inputArr[2][2]) ||
                (inputArr[0][2] == player && inputArr[0][2] == inputArr[1][1] && inputArr[1][1] == inputArr[2][0])
        ) {
            return true;
        }
        return false;
    }

    private static int[] getUserMove(char[][] inputArr) {
        int[] newMove = new int[2];
        Scanner scanner = new Scanner(System.in);
        boolean inputValid = false;
        do {
            System.out.println("Enter the coordinates: ");
            if (!scanner.hasNextInt()) {
                if (!scanner.hasNextInt()) {
                    System.out.println("You should enter numbers!");
                }
                System.out.println("You should enter numbers!");
            }
            newMove[0] = scanner.nextInt() - 1;
            newMove[1] = scanner.nextInt() - 1;

            if (!(newMove[0] >= 0 && newMove[0] < 3 && newMove[1] >= 0 && newMove[1] < 3)) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (inputArr[newMove[0]][newMove[1]] == 'X' || inputArr[newMove[0]][newMove[1]] == 'O') {
                System.out.println("This cell is occupied!");
            } else if (inputArr[newMove[0]][newMove[1]] != 'X' || inputArr[newMove[0]][newMove[1]] != 'O') {
                inputValid = true;
            }
        } while (!inputValid);

        return newMove;
    }

    private static void addUserMoveToInputArr(char[][] inputArr, int[] newMove, char currentPlayer) {
        inputArr[newMove[0]][newMove[1]] = currentPlayer;
        changePlayer(currentPlayer);
    }

}
