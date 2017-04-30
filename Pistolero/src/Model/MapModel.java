package Model;

public class MapModel extends Model{

    private int[][] mapdata;
    public MapModel()
    {
        mapdata = new int[25][19];
        for(int i=0;i<25;i++)
        {
            for(int j=0;j<19;j++)
            {
                mapdata[i][j] = 0;
            }
        }
    }
    public int[][] getMapData()
    {
        return mapdata;
    }

}
