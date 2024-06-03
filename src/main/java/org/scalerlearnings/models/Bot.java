package org.scalerlearnings.models;

import org.scalerlearnings.exception.GameOverException;
import org.scalerlearnings.service.botplayingstrategy.BotPlayingStrategy;
import org.scalerlearnings.service.botplayingstrategy.BotPlayingStrategyFactory;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */
public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;

    public Bot(int id, String name, char botSymbol, PlayerType playerType, BotDifficultyLevel botDifficultyLevel){
        super(id, name, botSymbol, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move makeMove(Board board) throws GameOverException {
        BotPlayingStrategy botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy();
        return botPlayingStrategy.makeMove(board, this);
    }

}
