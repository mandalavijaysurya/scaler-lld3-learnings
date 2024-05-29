package org.scalerlearnings.service.botplayingstrategy;

import org.scalerlearnings.exception.GameOverException;
import org.scalerlearnings.models.Board;
import org.scalerlearnings.models.Cell;
import org.scalerlearnings.models.CellStatus;
import org.scalerlearnings.models.Move;

import java.util.List;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */
public class RandomBotPlayingStrategy implements BotPlayingStrategy{

    @Override
    public Move makeMove(Board board) throws GameOverException{
        List<List<Cell>> matrix = board.getCellMatrix();
        for(int i = 0 ; i < matrix.size(); i++){
            for(int j = 0; j < matrix.size(); j++){
                if(matrix.get(i).get(j).getCellStatus().equals(CellStatus.EMPTY)){
                    return new Move();
                }
            }
        }

        throw new GameOverException("No cells to play with, GAME OVER !!!");
    }
}
