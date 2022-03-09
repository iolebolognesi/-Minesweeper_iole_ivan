package model;

public class Tile extends AbstractTile

{
    protected boolean isExplosive;

    public boolean getIsExplosive()
    {
        return isExplosive;
    }

    public void setIsExplosive(boolean a )
    {
        this.isExplosive = a;
    }


    @Override
    public boolean open() {
        return false;
    }

    @Override
    public void flag() {

    }

    @Override
    public void unflag() {

    }

    @Override
    public boolean isFlagged() {
        return false;
    }

    @Override
    public boolean isExplosive() {
        return false;
    }

    @Override
    public boolean isOpened() {
        return false;
    }
}
