package model;
//import java.lang.reflect.Array;
import java.util.Random;

public class Minesweeper extends AbstractMineSweeper
{
    protected int columns;
    protected int rows;
    protected int nrExplosions;
    protected AbstractTile[][] board;
    protected Random rd;



    @Override
    public int getWidth() {



        return columns;
    }

    @Override
    public int getHeight()

    {
        return rows;
    }

    @Override
    public void startNewGame(Difficulty level)
    {
        if (level == Difficulty.EASY)
        {
            this.rows = 8;
            this.columns = 8;
            this.nrExplosions =10;
        }
        else if(level == Difficulty.MEDIUM)
        {
            this.rows = 16;
            this.columns = 16;
            this.nrExplosions =40;
        }

        else
        {
            this.rows = 16;
            this.columns =30;
            this.nrExplosions =99;
        }

        board = new AbstractTile[rows][columns];
        for(int i=0; i< board.length; i++)
        {
            for(int j=0; j< board[i].length; j++)
            {
               /* if(board[i][j].getIsExplosive())
                {

                }
                else
                {
                    board[i][j] =new EmptyTile();
                }*/
                board[i][j] =new EmptyTile();
            }

        }
        rd = new Random();
        for(int i =0; i<=nrExplosions; i++)
        {
          int randomIndexRows = rd.nextInt(rows);
          int randomIndexColumns = rd.nextInt(columns);
          board[randomIndexRows][randomIndexColumns]= new ExplosiveTile();
          board[randomIndexRows][randomIndexColumns].setIsExplosive(true);
        }

        /*for(int i=0; i< board.length; i++)
        {
            for(int j=0; i< board[i].length; j++)
            {
                if(board[i][j].getIsExplosive())
                {

                }
                else
                {
                    board[i][j] =new EmptyTile();
                }

            }

        }*/
    }

    @Override
    public void startNewGame(int row, int col, int explosionCount)
    {
        this.rows = row;
        this.columns = col;
        this.nrExplosions = explosionCount;
        //board = new Tile[rows][columns];
        board = new AbstractTile[rows][columns];
        for(int i=0; i< board.length; i++)
        {
            for(int j=0; j< board[i].length; j++)
            {
               /* if(board[i][j].getIsExplosive())
                {

                }
                else
                {
                    board[i][j] =new EmptyTile();
                }*/
                board[i][j] =new EmptyTile();
            }

        }
        rd = new Random();
        for(int i =0; i<=nrExplosions; i++)
        {
            int randomIndexRows = rd.nextInt(rows);
            int randomIndexColumns = rd.nextInt(columns);
            board[randomIndexRows][randomIndexColumns]= new ExplosiveTile();
            board[randomIndexRows][randomIndexColumns].setIsExplosive(true);
        }



    }

    @Override
    public void toggleFlag(int x, int y) {

    }

    @Override
    public AbstractTile getTile(int x, int y)
    {


        return board[y][x];

    }

    @Override
    public void setWorld(AbstractTile[][] world) {
        board =  world;
    }

    @Override
    public void open(int x, int y) {

    }

    @Override
    public void flag(int x, int y) {
      board[y][x].setIsFlagged(true);
    }

    @Override
    public void unflag(int x, int y) {
        board[y][x].setIsFlagged(false);
    }

    @Override
    public void deactivateFirstTileRule() {

    }

    @Override
    public AbstractTile generateEmptyTile()
    {
        EmptyTile newTile = new EmptyTile();
        return newTile;

    }

    @Override
    public AbstractTile generateExplosiveTile() {
        return null;
    }
}
