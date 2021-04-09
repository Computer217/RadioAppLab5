package me.pgb.a2021_03_17_radio.models;

public class RadioStationArray {
    private static String[] stringList;



    public static RadioStation[] radioStations = {
            new RadioStation("http://stream.whus.org:8000/whusfm","WHUS", "USA", "https://images.unsplash.com/photo-1617889963256-c21f7a4acaa7?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=934&q=80"),
            new RadioStation("http://broadcast.miami/proxy/tkdisco?mp=/stream/;", "TKDISCO", "USA", "https://images.unsplash.com/photo-1617875473030-9a07df2e00fc?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=934&q=80"),
            new RadioStation("http://5.39.71.159:8413/;","TECHNOWORLD","USA","https://images.unsplash.com/photo-1589824783837-6169889fa20f?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80"),
            new RadioStation("http://kteq-streamer.sdsmt.edu:8000/play","COLLEGEMUSIC","USA","https://images.unsplash.com/photo-1510251197878-a2e6d2cb590c?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80")
    };

    public static String[] getArrayOfRadioLinks(){
        stringList = new String[radioStations.length];

        for (int i=0; i<radioStations.length; i++){
            stringList[i] = radioStations[i].getStreamLink();
        }
        return stringList;
    }

    public static String[] getArrayOfRadioNames(){
        stringList = new String[radioStations.length];

        for (int i = 0; i < radioStations.length; i++){
            stringList[i] = radioStations[i].getName();
        }

        return stringList;
    }

    public static String[] getArrayOfImageNames(){
        stringList = new String[radioStations.length];

        for (int i=0; i < radioStations.length; i++){
            stringList[i] = radioStations[i].getImageLink();
        }
        return stringList;
    }
}
