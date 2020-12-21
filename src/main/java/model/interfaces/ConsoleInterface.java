package model.interfaces;

import model.Game;
import model.Player;
import model.db.DataStorage;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleInterface {
    private static final DataStorage ds = new DataStorage();
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        //Этап авторизации
        System.out.print("Введите имя игрока белыми фигурами: ");
        String whitePlayerName = in.nextLine();
        System.out.print("Введите имя игрока черными фигурам: ");
        String blackPlayerName = in.nextLine();

        Player whitePlayer = ds.getPlayerByName(whitePlayerName);
        if (whitePlayer.getId() == null) {
            whitePlayer = ds.savePlayer(whitePlayer);
        }
        Player blackPlayer = ds.getPlayerByName(blackPlayerName);
        if (blackPlayer.getId() == null) {
            blackPlayer = ds.savePlayer(blackPlayer);
        }

        Game game = new Game(whitePlayer, blackPlayer);
        gameCycle(game);
    }

    private static void gameCycle(Game game) {
        while (true) {
            System.out.println(game.getChessBoard().toString());
            String currentPlayerColor = switch (game.getCurrentPlayerColor()) {
                case BLACK -> "черных";
                case WHITE -> "белых";
            };
            while (true) {
                System.out.println("Ход " + currentPlayerColor + " (впишите ход вида \"e2e4\").");
                String step = in.nextLine();
                step = step.trim().toLowerCase();
                Matcher matcher = Pattern.compile("[a-h][1-8][a-h][1-8]").matcher(step);
                if (matcher.find()) {
                    step = matcher.group();
                    int[] arrStep = convertStrStepToIntArr(step);
                    if (!game.move(arrStep[0], arrStep[1], arrStep[2], arrStep[3])) {
                        System.out.println("Невозможно совершить ход. Попробуйте еще раз.");
                    } else break; 
                } else System.out.println("Ход введен неправильно. Попробуйте еще раз.");
            }
            if (game.isEnded()) {
                System.out.println("Победили" + switch (game.getCurrentPlayerColor()) {
                    case BLACK -> " белые";
                    case WHITE -> " черные";
                } + "!");
                break;
            }
        }

    }

    private static int[] convertStrStepToIntArr(String step) {
        return new int[]{Integer.parseInt(String.valueOf(step.charAt(0) - 'a')),
                Integer.parseInt(String.valueOf(step.charAt(1))) - 1,
                Integer.parseInt(String.valueOf(step.charAt(2) - 'a')),
                Integer.parseInt(String.valueOf(step.charAt(3))) - 1};
    }
}
