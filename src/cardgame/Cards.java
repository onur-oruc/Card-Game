package cardgame;

// Cards - Maintains a collection of zero or more playing cards.
//         Provides facilities to create a full pack of 52 cards
//         and to shuffle the cards.
// author:
// date:
public class Cards
{
    final int NOOFCARDSINFULLPACK = 52;
    
    // properties
    Card[] cards;
    int    valid;  // number of cards currently in collection
    
    // constructors
    public Cards( boolean fullPack)
    {
        cards = new Card[ NOOFCARDSINFULLPACK ];
        valid = 0;
        
        if ( fullPack)
            createFullPackOfCards();
    }
    
    // methods
    public Card getTopCard()
    {
        Card tmp;

        if ( valid <= 0)
            return null;
        else
        {
            valid--;
            tmp = cards[valid];
            cards[valid] = null;
            return tmp;
        }
    }
    
    public boolean addTopCard( Card c)
    {
        if ( valid < cards.length)
        {
            cards[valid] = c;   // should this be cloned? 
            valid++;
            return true;
        }
        return false;
    }
    
    private void createFullPackOfCards()
    {
       for ( int i = 0; i < cards.length; i++) {
          addTopCard( new Card(i));
       }
    }
    
    public void shuffle()
    {
        int random1;
        int random2;
        
        for ( int i = 0; i < NOOFCARDSINFULLPACK; i++) {
            random1 = (int)(Math.random() * NOOFCARDSINFULLPACK );
            random2 = (int)(Math.random() * NOOFCARDSINFULLPACK );
            
            Card temp1 = cards[random1];
            
            cards[random1] = cards[random2];
            cards[random2] = temp1;
        }
    }  
} // end class Cards
