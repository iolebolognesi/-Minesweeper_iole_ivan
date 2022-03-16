package model;
//import java.lang.reflect.Array;
import java.util.Random;

public class Minesweeper extends AbstractMineSweeper
{
    protected int columns;
    protected int rows;
    protected int nrExplosions;
    protected int explosionsTotal;
    protected AbstractTile[][] board;
    protected Random rd;



    @Override
    public int getWidth()
    {
        return columns;
    }

    @Override
    public int getHeight()
    {
        return rows;
    }

    @Override
    public void startNewGame(Difficulty level) {

        this.nrExplosions=0;
        if (level == Difficulty.EASY) {
            this.rows = 8;
            this.columns = 8;
            this.explosionsTotal = 10;
        } else if (level == Difficulty.MEDIUM) {
            this.rows = 16;
            this.columns = 16;
            this.explosionsTotal = 40;
        } else {
            this.rows = 16;
            this.columns = 30;
            this.explosionsTotal = 99;
        }

        board = new AbstractTile[rows][columns];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new EmptyTile();

            }

        }


        rd = new Random();
        for (int i = 0; i < explosionsTotal; i++) {


            int randomIndexRows = rd.nextInt(rows);
            int randomIndexColumns = rd.nextInt(columns);

            if (board[randomIndexRows][randomIndexColumns].getIsExplosive())
            {
                i=i-1;

            }
            else
            {
                board[randomIndexRows][randomIndexColumns] = new ExplosiveTile();
                board[randomIndexRows][randomIndexColumns].setIsExplosive(true);
                nrExplosions= nrExplosions+ 1;

            }
            
        }

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


    @Override
    public void startNewGame(int row, int col, int explosionCount) {
        this.rows = row;
        this.columns = col;
        this.explosionsTotal= explosionCount;
        this.nrExplosions = 0;
        board = new AbstractTile[rows][columns];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new EmptyTile();
            }

        }
        rd = new Random();
        for (int i = 0; i < explosionsTotal; i++) {

                int randomIndexRows = rd.nextInt(rows);
                int randomIndexColumns = rd.nextInt(columns);

                if (board[randomIndexRows][randomIndexColumns].getIsExplosive())
                {
                    i=i-1;

                }
                else
                {
                    board[randomIndexRows][randomIndexColumns] = new ExplosiveTile();
                    board[randomIndexRows][randomIndexColumns].setIsExplosive(true);
                    nrExplosions= nrExplosions+ 1;
                }



        }
    }

    @Override
    public void toggleFlag(int x, int y)
    {
        AbstractTile tile = board[y][x];
        if(tile.isFlagged())
        {
            tile.setIsFlagged(false);
        }
        else
        {
            tile.setIsFlagged(true);
        }


    }

    @Override
    public AbstractTile getTile(int x, int y)
    {
        try
        {


                return board[y][x];

        }
        catch (IndexOutOfBoundsException e)
        {
            return null;

        }


    }

    @Override
    public void setWorld(AbstractTile[][] world)
    {
        board =  world;
    }

    @Override
    public void open(int x, int y)
    {

    }

    @Override
    public void flag(int x, int y)
    {
      board[y][x].setIsFlagged(true);
    }

    @Override
    public void unflag(int x, int y)
    {
        board[y][x].setIsFlagged(false);
    }

    @Override
    public void deactivateFirstTileRule()
    {

    }

    @Override
    public AbstractTile generateEmptyTile()
    {
        EmptyTile newTile = new EmptyTile();
        return newTile;

    }

    @Override
    public AbstractTile generateExplosiveTile()
    {
        ExplosiveTile newTile = new ExplosiveTile();
        return newTile;
    }
}
