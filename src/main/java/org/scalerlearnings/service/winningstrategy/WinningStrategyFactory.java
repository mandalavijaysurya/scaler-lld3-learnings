package org.scalerlearnings.service.winningstrategy;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */
public class WinningStrategyFactory {
    public WinningStrategy getWinningStrategy(WinningStrategies winningStrategy, int dimension){
        return new OrderOneWinningStrategy(dimension);
    }

}
