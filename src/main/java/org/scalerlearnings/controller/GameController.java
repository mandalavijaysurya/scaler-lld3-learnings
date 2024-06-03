package org.scalerlearnings.controller;

import org.scalerlearnings.exception.*;
import org.scalerlearnings.models.*;
import org.scalerlearnings.service.botplayingstrategy.BotPlayingStrategyFactory;
import org.scalerlearnings.service.winningstrategy.WinningStrategies;
import org.scalerlearnings.service.winningstrategy.WinningStrategyFactory;

import java.util.List;
import java.util.stream.IntStream;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */
public class GameController {
    public Game createGame(int dimension, List<Player> players, WinningStrategies winningStrategies) throws InvalidBotCountException, InvalidBoardException, DuplicateSymbolException, InvalidNumberOfPlayersException {
        return Game.builder()
                .dimension(dimension)
                .players(players)
                .winningStrategy(WinningStrategyFactory.getWinningStrategy(winningStrategies, dimension))
                .build();
    }
    public void displayBoard(Game game){
        game.getBoard().display();
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }
    public Player getWinner(Game game){
        return game.getWinner();
    }
    public Move executeMove(Game game, Player player) throws GameOverException, CellFilledException {
        Move move = null;
        while(move == null){
            move = player.makeMove(game.getBoard());
        }
        game.setNumberOfSymbols(game.getNumberOfSymbols() + 1);
        updateGameStatus(game);
        updateGameMoves(game, move);
        updateBoardStates(game);
        return move;
    }
    private void updateBoardStates(Game game){
        game.getBoards().add(new Board(game.getBoard()));
    }
    private void updateGameMoves(Game game, Move move){
        game.getMoves().add(move);
    }
    public Player checkWinner(Game game, Move lastPlayedMove){
        Player player = game.getWinningStrategy().checkWinner(game.getBoard(), lastPlayedMove);
        if(player != null){
            game.setWinner(player);
            game.setGameStatus(GameStatus.COMPLETED);
        }
        return player;
    }
    public Board undoMove(Game game, Move lastMove){
        game.getBoards().removeLast();
        game.getMoves().removeLast();
        int row  = lastMove.getCell().getRow();
        int col = lastMove.getCell().getRow();
        Cell cell = new Cell(row, col);
        game.getBoard().getCellMatrix().get(row).set(col, cell);
        game.setGameStatus(GameStatus.IN_PROGRESS);
        if(!game.getMoves().isEmpty())
            game.setCurrentPlayer(game.getMoves().getLast().getPlayer());
        game.setWinner(null);
        return game.getBoard();
    }
    public void replayTheGame(Game game){
        IntStream.range(game.getBoards().size()-1, 0).forEach(System.out::println);
        game.getBoards().forEach(board -> {
            try{
                Thread.sleep(1000);
                System.out.println("*---------------*");
                board.display();
                System.out.println("*---------------*");
            }catch (InterruptedException ie){
                System.out.println(ie.getMessage());
            }
        });
    }

    private void updateGameStatus(Game game){
        int numberOfSymbols = game.getNumberOfSymbols();
        int dimension = game.getBoard().getSize();
        if(numberOfSymbols == dimension * dimension){
            game.setGameStatus(GameStatus.DRAW);
        }
    }

}
