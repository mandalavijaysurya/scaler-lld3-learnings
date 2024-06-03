package org.scalerlearnings;

import org.scalerlearnings.controller.GameController;
import org.scalerlearnings.exception.*;
import org.scalerlearnings.models.*;
import org.scalerlearnings.service.winningstrategy.WinningStrategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Tictactoe {
    public static void main(String[] args) throws InvalidBotCountException, InvalidBoardException, DuplicateSymbolException, InvalidNumberOfPlayersException, GameOverException, CellFilledException {
        Scanner scanner = new Scanner(System.in);
        GameController controller = new GameController();
        System.out.println("Enter the dimension of game");
        int dimension = scanner.nextInt();
        System.out.println("Will there be any bot in the game ? Y/N");
        String isBotPresent = scanner.next();
        List<Player> playerList = new ArrayList<>();
        int iteratorNumber = dimension - 1;
        if(isBotPresent.equalsIgnoreCase("Y")){
            iteratorNumber--;
        }
        for(int i = 1; i <= iteratorNumber; i++){
            System.out.println("What is teh name of player, number :" + i);
            String playerName = scanner.next();
            System.out.println("What is the symbol of player, number :" + i);
            String symbol = scanner.next();
            playerList.add(new Player(i,playerName, symbol.charAt(0), PlayerType.HUMAN));
        }

        if(isBotPresent.equalsIgnoreCase("Y")){
            System.out.println("What is the name of BOT");
            String botName = scanner.next();
            System.out.println("What is the symbol of the BOT");
            String botSymbol = scanner.next();
            BotDifficultyLevel botDifficultyLevel = BotDifficultyLevel.EASY;
            Bot bot = new Bot(dimension, botName, botSymbol.charAt(0), PlayerType.BOT, botDifficultyLevel);
            playerList.add(bot);
        }
        // randomises the list of players
        Collections.shuffle(playerList);
        Game game = controller.createGame(dimension, playerList, WinningStrategies.ORDER_ONE);
        int playerIndex = 0;
        boolean isMoveUndoed = false;
        Player currentPlayer = null;
        while(controller.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){
            if(!isMoveUndoed){
                System.out.println("Current board status");
                controller.displayBoard(game);
                playerIndex = ++playerIndex % playerList.size();
                currentPlayer = playerList.get(playerIndex);
                game.setCurrentPlayer(currentPlayer);
            }
            System.out.println(currentPlayer.getName()+" is making a move");
            Move movePlayed = controller.executeMove(game, playerList.get(playerIndex));
            isMoveUndoed = false;
            if(currentPlayer.getPlayerType().equals(PlayerType.HUMAN)){
                System.out.println("Do you want to undo your move? Y/N");
                String isUndoRequired = scanner.next();
                if(isUndoRequired.equalsIgnoreCase("Y")){
                    Board board = controller.undoMove(game, movePlayed);
                    System.out.println("Move has been undo-ed, current status of board");
                    board.display();
                    isMoveUndoed = true;
                    continue;
                }
            }
            Player winner = controller.checkWinner(game, movePlayed);
            if(winner != null){
                controller.displayBoard(game);
                System.out.println("WINNER IS: " + winner.getName());
                break;
            }
        }
        System.out.println("Final board status: ");
        controller.displayBoard(game);
        System.out.println("Do you want a replay");
        String doReplay = scanner.next();
        if(doReplay.equalsIgnoreCase("Y"))
            controller.replayTheGame(game);
    }
}