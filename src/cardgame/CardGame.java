package cardgame;

import java.util.ArrayList;
import java.util.*;

// Cardgame
// author:
// date:
public class CardGame
{
    // properties
    Cards             fullPack;
    ArrayList<Player> players;
    ScoreCard         scoreCard;
    Cards[]           cardsOnTable;
    int               roundNo;
    int               turnOfPlayer;
    
    // constructors
    public CardGame( Player p1, Player p2, Player p3, Player p4)
    {
        players = new ArrayList<Player>();
        
        //create a full pack containing all 52 cards in order!
        fullPack = new Cards( true);
        
        fullPack.shuffle();
        
        // distribute shuffled cards to the 4 players
        for ( int i = 0; i < 52; i++) {
            if ( i % 4 == 3) {
                p1.add(fullPack.getTopCard());
            }
            if ( i % 4 == 2) {
                p2.add(fullPack.getTopCard());
            }
            if ( i % 4 == 1) {
                p3.add(fullPack.getTopCard());
            }
            if ( i % 4 == 0) {
                p4.add(fullPack.getTopCard());
            }     
        }
        
        players.add( p1);
        players.add( p2);
        players.add( p3);
        players.add( p4);
        
        
        scoreCard = new ScoreCard( 4);
       
        cardsOnTable = new Cards[13]; // "NULL POINTER EXCEPTION" if you dont initialize its variables in a loop !!!
        for ( int i = 0; i < 13; i++) {
            cardsOnTable[i] = new Cards(false);
        }
        
        roundNo = 1;
        turnOfPlayer = 0;
    }
    
    public boolean playTurn( Player p, Card c)
    {
        if ( !isGameOver()) {
            if ( isTurnOf( p)) {
                cardsOnTable[ roundNo-1].addTopCard( c);
                
                // if all of the 4 players played in current round, increment roundNo by 1 and update the score. 
                if ( turnOfPlayer % 4 == 3 ) {
                    updateScores();
                    roundNo++;
                }
                turnOfPlayer++;
                
                return true;
            }
            
            return false;
        }
        
        
        return false;
    }
    
    public boolean isTurnOf( Player p)
    {
        int turnOfPlayer;
        
        turnOfPlayer = getTurnOfPlayerNo();
        
        // player 1
        if ( p == players.get(0) && turnOfPlayer == 0) {
            return true;
        }
        
        // player 2
        if ( p == players.get(1) && turnOfPlayer == 1) {
            return true;
        }

        // player 3
        if ( p == players.get(2) && turnOfPlayer == 2) {
            return true;
        }

        // player 4
        if ( p == players.get(3) && turnOfPlayer == 3) {
            return true;
        }
        
        return false;
    }
    
    public boolean isGameOver()
    {
        if ( roundNo >= 14) 
            return true;
        return false;
    }
    
    public int getScore( int playerNumber)
    {
        int scoreOfPlayer;
        
        scoreOfPlayer = scoreCard.getScore( playerNumber);
        
        return scoreOfPlayer;
    }
    
    public String getName( int playerNumber)
    {
        return players.get( playerNumber).getName();
    }
    
    public int getRoundNo()
    {
        return roundNo;
    }
    
    public int getTurnOfPlayerNo()
    {
        return turnOfPlayer % 4;
    }
    
    public Player[] getWinners()
    {
        Player[] winnerPlayers = null;
       
        if ( isGameOver()) {

            int[] winners;
            
            winners = scoreCard.getWinners();
            winnerPlayers = new Player[ winners.length];
            
            for ( int i = 0; i < winners.length; i++) {
                winnerPlayers[i] = players.get( winners[i]);
            }
        }
        return winnerPlayers;
    }
    
    public String showScoreCard()
    {
        return scoreCard.toString();
    }
    
    private void updateScores() {
        Card[] cards;
        int winnerCount;
        int indexOfMax;
        int faceValue;
        
        winnerCount = 0;
        cards = new Card[4];
        indexOfMax = 0;
        faceValue = 0;
        
        // initialize the "cards" with the cards on the table
        for ( int i = 3; i >= 0; i--) {
            cards[i] = cardsOnTable[ roundNo - 1].getTopCard();
        }
        
        // find index of the card having the biggest face value
        for ( int i = 0; i < 4; i++) {
            if ( cards[i].compareTo( cards[ indexOfMax]) > 0) {
                indexOfMax = i;
            }
        }
        
        // face value of the maximum card
        faceValue = cards[ indexOfMax].getFaceValue();
        
        // count the winners in the current round
        for ( int i = 0; i < 4; i++) {
            if ( cards[i].compareTo( cards[ indexOfMax]) == 0) 
                winnerCount++;
        } 
        
        // update the scoreboard
        // if there is only 1 winner, add 4 points to the winner
        if ( winnerCount == 1) {
            for ( int i = 0; i < 4; i++) {
                if ( cards[i].getFaceValue() == faceValue) {
                    scoreCard.update(i, 4);
                }
            }
        }
        // if there are 2 winners, add 3 points to each winner
        else if ( winnerCount == 2) {
            for ( int i = 0; i < 4; i++) {
                if ( cards[i].getFaceValue() == faceValue) {
                    scoreCard.update(i, 3);
                }
            }
        }
        // if there are 3 winners, add 2 points to each winner
        else if ( winnerCount == 3) {
            for ( int i = 0; i < 4; i++) {
                if ( cards[i].getFaceValue() == faceValue) {
                    scoreCard.update(i, 2);
                }
            }
        }
        // if there are 4 winners, add 1 point to each winner
        else if ( winnerCount == 4) {
            for ( int i = 0; i < 4; i++) {
                if ( cards[i].getFaceValue() == faceValue) {
                    scoreCard.update(i, 1);
                }
            }
        }
    }
    
    public String toString() {
        String result;
        Player[] arr;
        
        arr = getWinners();
        result = "Winner(s) " + "\n";
        
        if ( isGameOver()) {
            for ( int i = 0; i < arr.length; i++) {
                result = result + arr[i].getName() + "\n";
            }
        }
        else {
            result += "Game is NOT over yet!";
        }
        
        return result;   
    }
}