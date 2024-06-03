package org.scalerlearnings.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */
public class Board {
    private int size;
    private List<List<Cell>> cellMatrix;

    public Board(Board board){
        this.size = board.size;
        this.cellMatrix = new ArrayList<>();
        for(int i = 0; i < size; i++){
            cellMatrix.add(new ArrayList<>());
            for(int j = 0; j< size; j++){
                cellMatrix.get(i).add(new Cell(board.getCellMatrix().get(i).get(j)));
            }
        }
    }

    public Board(int size) {
        this.size = size;
        this.cellMatrix = new ArrayList<>();
        for(int i = 0; i < size; i++){
            this.cellMatrix.add(new ArrayList<>());
            for(int j = 0; j < size; j++){
                this.cellMatrix.get(i).add(new Cell(i, j));
            }
        }
    }
    public void display(){
        for(List<Cell> cells: cellMatrix){
            for(Cell cell: cells){
                cell.display();
            }
            System.out.println();
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getCellMatrix() {
        return cellMatrix;
    }

    public void setCellMatrix(List<List<Cell>> cellMatrix) {
        this.cellMatrix = cellMatrix;
    }
}
