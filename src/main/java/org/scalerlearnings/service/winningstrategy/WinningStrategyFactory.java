package org.scalerlearnings.service.winningstrategy;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */
public class WinningStrategyFactory {
    public static WinningStrategy getWinningStrategy(WinningStrategies winningStrategy, int dimension){
        return switch (winningStrategy) {
            case ORDER_ONE -> new OrderOneWinningStrategy(dimension);
        };
    }

}
