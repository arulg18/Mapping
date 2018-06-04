public class TwelveNode {
    public enum node{
        crypto1,crypto2,balance1,balance2,relic;
    }
    public static int CRYPTO1 = 0;
    public static int CRYPTO2 = 1;
    public static int BALANCE1 = 2;
    public static int BALANCE2 = 3;
    public static int RELIC = 4;

    public int[][] coordinates = {//change this ordering to the correct one later
            {36,24},
            {24,48},
            {24,120},
            {24,84},
            {48,120}
    };
    public boolean[][]straight = {
            {true ,false,true ,true ,false},
                  {true ,false,true ,false},
                        {true ,true ,true },
                              {true ,false},
                                    {true }
    };


    public int[][] getCmd(int[]path){ //pass in an size 2 array containing beginning id and end id
        int j =1;
        for (int i = 0; i<2;i++) {
            if (straight[path[i]][path[j]])//if the path is straight
            {
                int[][]retval = {{i},coordinates[path[0]],coordinates[path[1]]};
                return retval;
            }
            else if(path[i]==CRYPTO1&& path[j]==CRYPTO2)
            {
                int[][] retval = {{i},coordinates[CRYPTO1],{36,72},coordinates[CRYPTO1]};
                return retval;
            }
            else if(path[i]==CRYPTO1&&path[j]==RELIC)
            {
                //hardcode path
            }
            else if(path[i]==CRYPTO2&&path[j]==BALANCE1)
            {
                //hardcode path
            }
            else if(path[i]==CRYPTO2&&path[j]==RELIC)
            {
                //hardcode path
            }
            else if(path[i]==BALANCE2&&path[j]==RELIC)
            {
                //hardcode path
            }
            j=0;
        }
        return new int[1][2];
    }

    public int[][] getCommands(int[]path){
        int[][] temp = getCmd(path);
        int[][] retval = new int [temp.length][2];
        if(temp[0][0]==0)
        {
            for(int i = 0;i<=temp.length-1;i++)
            {
                retval[i]=temp[temp.length-1-i];
            }
        }
        else
        {
            for(int i = 0;i<=temp.length-1;i++)
            {
                retval[temp.length-1-i]=temp[i];
            }
        }
        return retval;
    }
}
