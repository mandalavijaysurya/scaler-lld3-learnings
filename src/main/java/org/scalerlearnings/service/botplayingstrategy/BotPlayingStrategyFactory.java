package org.scalerlearnings.service.botplayingstrategy;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */
public class BotPlayingStrategyFactory {
    public BotPlayingStrategy getBotPlayingStrategy(){
        return new RandomBotPlayingStrategy();
    }

}
