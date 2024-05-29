package org.scalerlearnings.service.botplayingstrategy;

import org.scalerlearnings.exception.GameOverException;
import org.scalerlearnings.models.Board;
import org.scalerlearnings.models.Move;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */
public interface BotPlayingStrategy {
    Move makeMove(Board board) throws GameOverException;

}
