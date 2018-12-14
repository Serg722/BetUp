package com.hfad.betup;

import java.util.ArrayList;
import java.util.Date;

public class BetToday {
    public final static int POSITIVE_COLOR = 0xFF007404;
    public final static int NEGATIVE_COLOR = 0xFFC00117;
    public final static int RETURN_COLOR = 0xFFFDDE10;
    public final static int NO_HISTORY = 0xFFFFFFFF;
    //test
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

    public BetToday() {
    }

    public BetToday(String country, String time, String teamOwner, String teamGuest,
                    String resultMatchOwner, String resultMatchGuest, String betPrediction, String keff,
                    String state, String flag, String flagBonus) {
        this.country = country;
        this.time = time;
        this.teamOwner = teamOwner;
        this.teamGuest = teamGuest;
        this.resultMatchOwner = resultMatchOwner;
        this.resultMatchGuest = resultMatchGuest;
        this.betPrediction = betPrediction;
        this.keff = keff;
        this.state=state;
        this.flag = flag;
        this.flagBonus = flagBonus;

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
}

