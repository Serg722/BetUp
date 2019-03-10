package com.hfad.betup;



public class BetToday {
    public final static int POSITIVE_COLOR = 0xFF007404;
    public final static int NEGATIVE_COLOR = 0xFFC00117;
    public final static int RETURN_COLOR = 0xFFFDDE10;
    public final static int NO_HISTORY = 0xFFFFFFFF;
    private String country;
    private String time;
    private String teamOwner;
    private String teamGuest;
    private String resultMatchOwner;
    private String resultMatchGuest;
    private String betPrediction;
    private String keff;
    private String state;
    private String flag;
    private String flagBonus;
    private String date;

    public BetToday() {
    }


    public String getCountry() {
        return country;
    }

    public String getTime() {
        return time;
    }

    public String getTeamOwner() {
        return teamOwner;
    }

    public String getTeamGuest() {
        return teamGuest;
    }

    public String getResultMatchOwner() {
        return resultMatchOwner;
    }

    public String getResultMatchGuest() {
        return resultMatchGuest;
    }

    public String getBetPrediction() {
        return betPrediction;
    }

    public String getKeff() {
        return keff;
    }

    public String getState() {
        return state;
    }

    public String getFlag() {
        return flag;
    }

    public String getFlagBonus() {
        return flagBonus;
    }

   public String getDate() {
        return date;
    }
}

