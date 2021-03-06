package model;
//import java.lang.reflect.Array;
import java.awt.*;
import java.util.Random;

public class Minesweeper extends AbstractMineSweeper
{
    protected boolean isFirsttile;
    protected int nrExplosions;
    protected int explosionsTotal;
    protected AbstractTile[][] board;
    protected Random rd;
    protected int nrFlags;



    @Override
    public int getWidth()
    {
        return board[0].length;
    }

    @Override
    public int getHeight()
    {
        return board.length;
    }

    @Override
    public void startNewGame(Difficulty level) {

        if (level == Difficulty.EASY) {
            startNewGame(8,8,10);
        } else if (level == Difficulty.MEDIUM) {

            startNewGame(16,16,40);
        } else {
            startNewGame(16,30,99);
        }

    }







    @Override
    public void startNewGame(int row, int col, int explosionCount)
    {

        deactivateFirstTileRule();
        this.nrFlags=explosionCount;

        this.explosionsTotal= explosionCount;
        this.nrExplosions = 0;
        board = new AbstractTile[row][col];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new EmptyTile();
            }

        }
        rd = new Random();
        for (int i = 0; i < explosionsTotal; i++) {

                int randomIndexRows = rd.nextInt(board.length);
                int randomIndexColumns = rd.nextInt(board[0].length);

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
        viewNotifier.notifyNewGame(row, col);
    }

    @Override
    public void toggleFlag(int x, int y)
    {


        AbstractTile tile = board[y][x];
        if(tile.isFlagged())
        {
            tile.setIsFlagged(false);
            this.nrFlags++;

            viewNotifier.notifyUnflagged(x,y);
            viewNotifier.notifyFlagCountChanged(nrFlags);
        }
        else
        {
            tile.setIsFlagged(true);
            this.nrFlags--;
            viewNotifier.notifyFlagged(x,y);
            viewNotifier.notifyFlagCountChanged(nrFlags);
        }


        int countOpenCells =0;
        for (int j = 0; j < board.length; j++)
        {
            for (int i = 0; i < board[0].length; i++)
            {
                if(board[j][i].isOpened() || board[j][i].isFlagged())
                {
                    countOpenCells = countOpenCells+1;
                }
                if(countOpenCells == board.length*board[0].length)
                {
                    viewNotifier.notifyGameWon();
                }
            }

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


    public void openFirstTile(int x, int y)
    {
        isFirsttile = false;
        boolean check = false;

        if (board[y][x].getIsExplosive())
        {
            board[y][x] = new EmptyTile();
            board[y][x].setIsExplosive(false);
            rd = new Random();
            while (!check)
            {
                int randomIndexRows = rd.nextInt(board.length);
                int randomIndexColumns = rd.nextInt(board[0].length);
                if (randomIndexRows != y && randomIndexColumns != x && !board[randomIndexRows][randomIndexColumns].isExplosive()) {
                    check = true;
                    board[randomIndexRows][randomIndexColumns] = new ExplosiveTile();
                    board[randomIndexRows][randomIndexColumns].setIsExplosive(true);
                }

            }

        }
        else
        {

        }

        int counter=0;

        for (int i = x-1; i <= x+1; i++)
        {
            for (int j = y-1; j <= y+1; j++)
            {
                if(i>=0 && i< board[0].length && j>=0 && j<board.length)
                {
                    if( board[j][i].getIsExplosive())
                    {
                        counter +=1;
                    }
                    else{}

                }
                else{}
            }
        }


        board[y][x].isOpened();
        board[y][x].open();
        viewNotifier.notifyOpened(x,y,counter);

        if(counter==0)
        {
            for (int i = x-1; i <= x+1; i++)
            {
                for (int j = y-1; j <= y+1; j++)
                {
                    if(i>=0 && i< board[0].length && j>=0 && j<board.length)
                    {
                       // board[j][i].isOpened();
                        //board[j][i].open();
                        //viewNotifier.notifyOpened(i,j,countHelp(i,j));
                        if(!board[j][i].isOpened() )
                        {
                            open(i,j);
                        }


                    }
                    else{}
                  }
            }
        }
    }


    public int countHelp(int x,int y)
    {
        int counter2 = 0;

            for (int i = x - 1; i <= x + 1; i++)
            {
                for (int j = y - 1; j <= y + 1; j++)
                {
                    if (i>=0 && i< board[0].length && j>=0 && j<board.length)
                    {
                        if (board[j][i].getIsExplosive())
                        {
                            counter2 += 1;
                        }
                        else {}

                    }
                    else {}
                }
            }
            return counter2;
    }

    @Override
    public void open(int x, int y)
    {

        if(x< board[0].length && y< board.length && x>=0 && y>=0)
        {


            int counter=0;

            for (int i = x-1; i <= x+1; i++)
            {
                for (int j = y-1; j <= y+1; j++)
                {
                    if(i>=0 && i< board[0].length && j>=0 && j<board.length)
                    {
                        if( board[j][i].getIsExplosive())
                        {
                            counter +=1;
                        }
                        else{}

                    }
                    else{}
                }
            }



            if (isFirsttile)
            {
                openFirstTile(x,y);
            }
            else
            {
                //board[y][x].isOpened();
                board[y][x].open();


                viewNotifier.notifyOpened(x, y, counter);

                if(counter==0)
                {
                    for (int i = x-1; i <= x+1; i++)
                    {
                        for (int j = y-1; j <= y+1; j++)
                        {
                            if(i>=0 && i< board[0].length && j>=0 && j<board.length)
                            {

                                //board[j][i].isOpened();
                                //board[j][i].open();
                                //viewNotifier.notifyOpened(i,j,countHelp(i,j));
                                if(!board[j][i].isOpened())
                                {
                                    open(i,j);
                                }


                            }
                            else{}
                        }
                    }

                }

                if (board[y][x].getIsExplosive())
                {
                    viewNotifier.notifyExploded(x, y);

                    for (int j = 0; j < board.length; j++)
                    {
                        for (int i = 0; i < board[0].length; i++)
                        {
                            if(board[j][i].getIsExplosive())
                            {
                                board[j][i].isOpened();
                                board[j][i].open();
                                viewNotifier.notifyExploded(i, j);

                            }
                        }

                    }

                    viewNotifier.notifyGameLost();
                }
            }


            // notify game won starts here
            int countOpenCells =0;
            for (int j = 0; j < board.length; j++)
            {
                for (int i = 0; i < board[0].length; i++)
                {
                    if(board[j][i].isOpened() || board[j][i].isFlagged())
                    {
                        countOpenCells = countOpenCells+1;
                    }
                    if(countOpenCells == board.length*board[0].length)
                    {
                        viewNotifier.notifyGameWon();
                    }
                }

            }

        }

        else{}
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
        this.isFirsttile = true;

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
