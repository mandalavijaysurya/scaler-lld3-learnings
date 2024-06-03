package org.scalerlearnings.service.botplayingstrategy;

import org.scalerlearnings.exception.GameOverException;
import org.scalerlearnings.models.*;

import java.util.List;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */
public class RandomBotPlayingStrategy implements BotPlayingStrategy{

    @Override
    public Move makeMove(Board board, Player player) throws GameOverException{
        List<List<Cell>> matrix = board.getCellMatrix();
        for(int i = 0 ; i < matrix.size(); i++){
            for(int j = 0; j < matrix.size(); j++){
                if(matrix.get(i).get(j).getCellStatus().equals(CellStatus.EMPTY)){
                    matrix.get(i).get(j).setCellStatus(CellStatus.FILLED);
                    matrix.get(i).get(j).setPlayer(player);
                    Move lastPlayedMove = new Move(i, j, player);
                    Cell latestCell = new Cell(i,j);
                    latestCell.setCellStatus(CellStatus.FILLED);
                    lastPlayedMove.setCell(latestCell);
                    lastPlayedMove.setPlayer(player);
                    return lastPlayedMove;
                }
            }
        }

        throw new GameOverException("No cells to play with, GAME OVER !!!");
    }
}
