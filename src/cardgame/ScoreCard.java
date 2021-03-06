package cardgame;

// ScoreCard - Maintains one integer score per player, for any number of players
//             Caution: invalid playernumbers result in run-time exception!
// author:
// date:
public class ScoreCard
{
    // properties
    int[] scores;
    
    // constructors
    public ScoreCard( int noOfPlayers)
    {
        scores = new int[noOfPlayers];
        
        // init all scores to zero
        for ( int i = 0; i < scores.length; i++)
            scores[i] = 0;
    }
    
    // methods
    public int getScore( int playerNo)
    {
        return scores[ playerNo];
    }
    
    public void update( int playerNo, int amount)
    {
        scores[playerNo] += amount;
    }
    
    public String toString()
    {
        String s;
        s = "\n"
            + "_____________\n"
            + "\nPlayer\tScore\n"
            + "_____________\n";
        
        for ( int playerNo = 1; playerNo  < scores.length + 1; playerNo++)
        {
            s = s + playerNo + "\t" + scores[playerNo - 1] + "\n";
        }
        
        s += "_____________\n";
        return s;
    }
    
    public int[] getWinners()
    {
        int countOfWinners = 0;
        int maxPoint = -1;
        int[] winners;
        
        // find max point
        for ( int i = 0; i < 4; i++ ) { 
            if ( scores[i] > maxPoint) {
                maxPoint = scores[i];
            }
        }
        
        // find number of winners
        for ( int i = 0; i < 4; i++) {
            if ( scores[i] == maxPoint) {
                countOfWinners++;
            }
        }
        
        winners = new int[countOfWinners];
        
        // set the winners
        int a = 0;
        for ( int i = 0; i < 4; i++) {
            if ( scores[i] == maxPoint) {
                winners[a] = i;
                a++;
            }
        }
        
        return winners;
    }
    
    
} // end class ScoreCard
