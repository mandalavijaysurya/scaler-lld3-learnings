package org.scalerlearnings.models;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */
public class Cell {
    private int row;
    private int column;
    private CellStatus cellStatus;
    private Player player;

    public Cell(int row, int column) {
        this.row = row;
        this.cellStatus = CellStatus.EMPTY;
        this.column = column;
    }
    public void display(){
        if(player == null)
            System.out.print("|   |");
        else
            System.out.print("| " + player.getSymbol() + " |");
    }
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
