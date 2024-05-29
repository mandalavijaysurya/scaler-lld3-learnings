package org.scalerlearnings.service.winningstrategy;

import org.scalerlearnings.models.Board;
import org.scalerlearnings.models.Move;
import org.scalerlearnings.models.Player;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */
public interface WinningStrategy {
    Player checkWinner(Board board, Move lastMove);

}
