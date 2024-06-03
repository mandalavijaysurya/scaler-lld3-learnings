package org.scalerlearnings.models;

import org.scalerlearnings.exception.CellFilledException;
import org.scalerlearnings.exception.GameOverException;

import java.util.Scanner;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */
public class Player {
    private int id;
    private String name;
    private Character symbol;

    private PlayerType playerType;
    public Player(){

    }

    public Player(int id, String name, Character symbol, PlayerType playerType) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
    public Move makeMove(Board board) throws GameOverException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the row for your move");
        int row = sc.nextInt();
        System.out.println("Enter the column for your move");
        int col = sc.nextInt();
        Cell pickedCell = board.getCellMatrix().get(row).get(col);
        try{
            if(pickedCell.getCellStatus().equals(CellStatus.FILLED)){
                throw new CellFilledException("Selected cell was already filled, please select other...");
            }
        }catch (CellFilledException cellFilledException){
            System.out.println(cellFilledException.getMessage());
            return null;
        }
        pickedCell.setCellStatus(CellStatus.FILLED);
        board.getCellMatrix().get(row).get(col).setPlayer(this);
        Move lastPlayedMove = new Move(row, col, this);
        Cell latestCell = new Cell(row, col);
        latestCell.setCellStatus(CellStatus.FILLED);
        lastPlayedMove.setCell(latestCell);
        return lastPlayedMove;
    }
}
