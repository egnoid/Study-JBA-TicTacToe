import java.util.Arrays;
import java.util.Scanner;

//package tictactoe;

public class TicTacToe {
    static final int GAME_FIELD = 9;
    static boolean stepX = true;
    static boolean inPlay = true;

    public static void main(String[] args) {
        // create an empty field;
        char[] gameStatement = new char[9];
        Arrays.fill(gameStatement, '_');
        //this option allows to start from pre-filled game field
        //char[] gameStatement = readString();
        printGameField(gameStatement);


        //game loop
        while (inPlay) {
            gameStatement = userMove(gameStatement);
            printGameField(gameStatement);
            analyzeGame(gameStatement);
        }


        //analyzeGame(gameStatement);
    }

    public static char[] readString() {
        char[] positions = new char[GAME_FIELD];
        Scanner read = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String input = read.next();
        System.out.println();
        for (int j = 0; j < GAME_FIELD; j++) {
            positions[j] = input.charAt(j);
            if (positions[j] != 'X' && positions[j] != 'O' && positions[j] != '_') {
                System.out.println("Incorrect input data. Allowed symbols are: X O _");
            }

        }
        return positions;
    }

    public static char[] userMove(char[] positions) {
        int[][] pos2line = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
        Scanner userInput = new Scanner(System.in);

        int uX;
        int uY;
        //ask for input until get correct data

        char marker = stepX ? 'X' : 'O';
        while (true) {
            System.out.print("Enter the coordinates:");
            if (userInput.hasNextInt()) {
                uX = userInput.nextInt();
            } else {
                System.out.println("You should enter numbers!");
                userInput.nextLine();
                continue;
            }
            if (userInput.hasNextInt()) {
                uY = userInput.nextInt();
            } else {
                System.out.println("You should enter numbers!");
                userInput.nextLine();
                continue;
            }
            if (uX < 1 || uX > 3 || uY < 1 || uY > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                userInput.nextLine();
                continue;
            } else {
                if (positions[pos2line[uX-1][uY-1]] != '_') {
                    System.out.println("This cell is occupied! Choose another one!");
                    userInput.nextLine();
                    continue;
                } else {
                    positions[pos2line[uX-1][uY-1]] = marker;
                    stepX = !stepX;
                    break;
                }
            }


        }


        return positions;

    }

    static void printGameField(char[] positions) {
        if (positions.length != GAME_FIELD) {
            System.out.println("WronG input data. Game field size if fixed, and equal to 9");
            return;
        }
        System.out.println("---------");
        System.out.println("| " + positions[0] + " " + positions[1] + " " + positions[2] + " |");
        System.out.println("| " + positions[3] + " " + positions[4] + " " + positions[5] + " |");
        System.out.println("| " + positions[6] + " " + positions[7] + " " + positions[8] + " |");
        System.out.println("---------");

    }

    static void analyzeGame(char[] positions) {
        int numOfX = 0;
        int numOfO = 0;
        for (int i = 0; i < GAME_FIELD; i++) {
            //count X
            if (positions[i] == 'X') {
                numOfX++;
            }
            //count O
            if (positions[i] == 'O') {
                numOfO++;
            }
        }
        if (Math.abs(numOfO - numOfX) > 1) {
            System.out.println("Impossible");
            return;
        }
        boolean isXWin = false;
        boolean isOWin = false;
        if (positions[0] == 'X' && positions[1] == 'X' && positions[2] == 'X') {isXWin = true;}
        if (positions[3] == 'X' && positions[4] == 'X' && positions[5] == 'X') {isXWin = true;}
        if (positions[6] == 'X' && positions[7] == 'X' && positions[8] == 'X') {isXWin = true;}
        if (positions[0] == 'X' && positions[3] == 'X' && positions[6] == 'X') {isXWin = true;}
        if (positions[1] == 'X' && positions[4] == 'X' && positions[7] == 'X') {isXWin = true;}
        if (positions[2] == 'X' && positions[5] == 'X' && positions[8] == 'X') {isXWin = true;}
        if (positions[0] == 'X' && positions[4] == 'X' && positions[8] == 'X') {isXWin = true;}
        if (positions[6] == 'X' && positions[4] == 'X' && positions[2] == 'X') {isXWin = true;}

        if (positions[0] == 'O' && positions[1] == 'O' && positions[2] == 'O') {isOWin = true;}
        if (positions[3] == 'O' && positions[4] == 'O' && positions[5] == 'O') {isOWin = true;}
        if (positions[6] == 'O' && positions[7] == 'O' && positions[8] == 'O') {isOWin = true;}
        if (positions[0] == 'O' && positions[3] == 'O' && positions[6] == 'O') {isOWin = true;}
        if (positions[1] == 'O' && positions[4] == 'O' && positions[7] == 'O') {isOWin = true;}
        if (positions[2] == 'O' && positions[5] == 'O' && positions[8] == 'O') {isOWin = true;}
        if (positions[0] == 'O' && positions[4] == 'O' && positions[8] == 'O') {isOWin = true;}
        if (positions[6] == 'O' && positions[4] == 'O' && positions[2] == 'O') {isOWin = true;}

        if (isOWin && isXWin) {
            System.out.println("Impossible");
        } else if (!isOWin && !isXWin && (numOfO + numOfX) < 9) {
            //System.out.println("Game not finished");

        } else if (!isOWin && !isXWin && (numOfO + numOfX) == 9) {
            System.out.println("Draw");
            inPlay = false;
        } else if (isOWin && !isXWin){
            System.out.println("O wins");
            inPlay = false;
        } else if (!isOWin && isXWin) {
            System.out.println("X wins");
            inPlay = false;
        }

    }

}