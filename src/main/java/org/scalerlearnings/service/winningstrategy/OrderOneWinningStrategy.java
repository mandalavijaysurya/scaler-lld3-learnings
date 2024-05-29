package org.scalerlearnings.service.winningstrategy;

import org.scalerlearnings.models.Board;
import org.scalerlearnings.models.Move;
import org.scalerlearnings.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */
public class OrderOneWinningStrategy implements WinningStrategy{
    private int dimension;
    private List<HashMap<Character, Integer>> rowHashMaps;
    private List<HashMap<Character, Integer>> columnHashMaps;
    private HashMap<Character, Integer> topLeftHashMap;
    private HashMap<Character, Integer> topRightHashMap;
    private HashMap<Character, Integer> cornerHashMap;
    public OrderOneWinningStrategy(int dimension){
        this.dimension = dimension;
        rowHashMaps = new ArrayList<>();
        columnHashMaps = new ArrayList<>();
        for(int i = 0; i < dimension; i++){
            rowHashMaps.add(new HashMap<>());
            columnHashMaps.add(new HashMap<>());
        }
        topLeftHashMap = new HashMap<>();
        topRightHashMap = new HashMap<>();
        cornerHashMap = new HashMap<>();
    }
    public boolean isTopLeftDiagonalCell(int row, int col){
        return row == col;
    }
    public boolean isTopRightDiagonalCell(int row, int col){
        return (row + col) == dimension;
    }
    public boolean isCornerCell(int row, int col){
        if(row == 0 || row == dimension - 1){
            return col == 0 || col == dimension - 1;
        }
        return false;
    }
    @Override
    public Player checkWinner(Board board, Move lastMove) {
        Player player = lastMove.getPlayer();
        char symbol = player.getSymbol();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getColumn();
        if(checkRowWin(row, symbol)){
            return player;
        }
        else if(checkColumnWin(col, symbol)){
            return player;
        }
        else if(isTopLeftDiagonalCell(row, col) && checkTopLeftDiagonalWin(symbol)){
            return player;
        }
        else if(isTopRightDiagonalCell(row, col) && checkRightTopDiagonalWin(symbol)){
            return player;
        }
        else if(isCornerCell(row, col) && checkCornerWin(symbol)){
            return player;
        }
        return null;
    }
    private boolean checkRowWin( int row, char symbol){
        if(!rowHashMaps.get(row).containsKey(symbol))
            rowHashMaps.get(row).put(symbol, 0);
        rowHashMaps.get(row).put(symbol, rowHashMaps.get(row).get(symbol) + 1);
        return  (rowHashMaps.get(row).get(symbol) == dimension);
    }
    private boolean checkColumnWin(int col, char symbol){
        if(!columnHashMaps.get(col).containsKey(symbol))
            columnHashMaps.get(col).put(symbol, 0);
        columnHashMaps.get(col).put(symbol, columnHashMaps.get(col).get(symbol) + 1);
        return  (columnHashMaps.get(col).get(symbol) == dimension);
    }
    private boolean checkTopLeftDiagonalWin(char symbol){
        topLeftHashMap.putIfAbsent(symbol, 0);
        topLeftHashMap.put(symbol, topLeftHashMap.get(symbol) + 1);
        return topLeftHashMap.get(symbol) == dimension;
    }
    private boolean checkRightTopDiagonalWin(char symbol){
        topRightHashMap.putIfAbsent(symbol, 0);
        topRightHashMap.put(symbol, topRightHashMap.get(symbol) + 1);
        return topRightHashMap.get(symbol) == dimension;
    }
    private boolean checkCornerWin( char symbol){
        cornerHashMap.putIfAbsent(symbol, 0);
        cornerHashMap.put(symbol, cornerHashMap.get(symbol) + 1);
        return topRightHashMap.get(symbol) == 4;
    }

}
