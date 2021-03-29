package me.pgb.a2021_03_17_radio.models;

public class RadioStationArray {
    private static String[] stringList;



    public static RadioStation[] radioStations = {
            new RadioStation("http://stream.whus.org:8000/whusfm","WHUS", "USA", "https://images.unsplash.com/photo-1616899822079-5e40267ebbc4?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=934&q=80"),
            new RadioStation("http://broadcast.miami/proxy/tkdisco?mp=/stream/;", "TKDISCO", "USA", "https://images.unsplash.com/photo-1594623930572-300a3011d9ae?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80"),
            new RadioStation("http://5.39.71.159:8413/;","TECHNOWORLD","USA","https://images.unsplash.com/photo-1543379344-402b42ddbe8a?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1300&q=80"),
            new RadioStation("http://kteq-streamer.sdsmt.edu:8000/play","COLLEGEMUSIC","USA","https://images.unsplash.com/flagged/photo-1561203490-062f3051d6d7?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=934&q=80")
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
