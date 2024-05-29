package org.scalerlearnings.models;

import org.scalerlearnings.exception.DuplicateSymbolException;
import org.scalerlearnings.exception.InvalidBoardException;
import org.scalerlearnings.exception.InvalidBotCountException;
import org.scalerlearnings.exception.InvalidNumberOfPlayersException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */
public class Game {
    private Board board;
    private List<Player> players;
    private Player currentPlayer;
    private GameStatus gameStatus;
    private Player winner;
    private List<Move> moves;
    private List<Board> boards;
    private WinningStrategy winningStrategy;

    private Game(Board board, List<Player> players, WinningStrategy winningStrategy) {
        this.board = board;
        this.players = players;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.boards = new ArrayList<>();
        this.winningStrategy = winningStrategy;
    }

    public static Builder builder(){
        return new Builder();
    }

    static class Builder{
        private int dimension;
        private List<Player> players;
        private WinningStrategy winningStrategy;

        public Builder dimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder players(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder winningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategy = winningStrategy;
            return this;
        }
        public void validateBotCount() throws InvalidBotCountException {
            int botCount = 0;
            for(Player player: players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
                if(botCount > 1)
                    throw new InvalidBotCountException("Bot count cannot be more than 1 currently : " + botCount);
            }
        }

        private void validateBoard() throws InvalidBoardException{
            if(dimension < 3 || dimension > 10)
                throw new InvalidBoardException("Board size should be >= 3 and <= 10, currently: " + dimension);
        }

        private void validatePlayerNumber() throws InvalidNumberOfPlayersException {
            if(players.size() != dimension - 1)
                throw new InvalidNumberOfPlayersException("Number of players is invalid, currently: " + players.size());
        }

        private void validateDuplicateSymbol() throws DuplicateSymbolException {
            HashSet<Character> symbolSet = new HashSet<>();
            for(Player player: players){
                symbolSet.add(player.getSymbol());
            }
            if(symbolSet.size() != players.size())
                throw new DuplicateSymbolException("All players should have unique symbol");
        }

        private void validate() throws DuplicateSymbolException, InvalidBotCountException, InvalidBoardException, InvalidNumberOfPlayersException {
            validateBoard();
            validateBotCount();
            validateDuplicateSymbol();
            validatePlayerNumber();
        }
        public Game build() throws InvalidBotCountException, InvalidBoardException, DuplicateSymbolException, InvalidNumberOfPlayersException {
            validate();
            return new Game(new Board(dimension), players, winningStrategy);
        }
    }

}
