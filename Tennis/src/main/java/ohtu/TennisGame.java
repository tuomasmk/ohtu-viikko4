package ohtu;

public class TennisGame {
    
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == player1Name)
            player1Score += 1;
        else
            player2Score += 1;
    }
    
    private boolean isTie() {
        return player1Score == player2Score;
    }
    private String tieToText() {
        switch (player1Score) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            case 3:
                return "Forty-All";
            default:
                return "Deuce";
        }
    }
    
    private boolean isAdvantageOrWin() {
        return (player1Score>=4 || player2Score>=4);
    }
    
    private String advantageOrWinToText() {
        int scoreDifference = player1Score-player2Score;
        if (scoreDifference==1) {
            return "Advantage player1";
        } else if (scoreDifference ==-1) {
            return "Advantage player2";
        } else if (scoreDifference>=2) {
            return "Win for player1";
        } else {
            return "Win for player2";
        }
    }
    
    private String scoreToText(int score) {
        switch(score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return ""; //Never used!
        }
    }

    public String getScore() {
        if (isTie()) {
            return tieToText();
        } else if (isAdvantageOrWin()) {
            return advantageOrWinToText();
        } else {
            String score = "";
            score += scoreToText(player1Score);
            score += "-";
            score += scoreToText(player2Score);
            return score;
        }
    }
}