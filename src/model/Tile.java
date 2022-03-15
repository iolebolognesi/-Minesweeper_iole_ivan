package model;

public class Tile extends AbstractTile

{
    protected boolean isExplosive;
    protected boolean isFlagged;


    public boolean getIsExplosive()
    {
        return isExplosive;
    }

    public void setIsExplosive(boolean a )
    {
        this.isExplosive = a;
    }

    public void setIsFlagged(boolean a )
    {
        this.isFlagged = a;
    }

    @Override
    public boolean open()
    {
        return false;
    }

    @Override
    public void flag()
    {
        this.isFlagged= true;

    }

    @Override
    public void unflag()
    {
        this.isFlagged= false;

    }

    @Override
    public boolean isFlagged() {

        return isFlagged;
    }

    @Override
    public boolean isExplosive() {

        return isExplosive;
    }

    @Override
    public boolean isOpened()
    {
        return false;
    }
}
