package com.example.datvl.testcn.Model;

public class VideoYouTub {
    private String Title;
    private String Thumbnail;
    private String Channel;
    private String IdVideo;

    public VideoYouTub(String title, String thumbnail, String channel, String idVideo) {
        Title = title;
        Thumbnail = thumbnail;
        Channel = channel;
        IdVideo = idVideo;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getChannel() {
        return Channel;
    }

    public void setChannel(String channel) {
        Channel = channel;
    }

    public String getIdVideo() {
        return IdVideo;
    }

    public void setIdVideo(String idVideo) {
        IdVideo = idVideo;
    }
}
