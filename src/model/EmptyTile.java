package model;

public class EmptyTile extends Tile

{
    private boolean isExplosive;

    public EmptyTile()
    {
        isExplosive=false;

    }

    public boolean getIsExplosive()
    {
        return isExplosive;
    }
}
