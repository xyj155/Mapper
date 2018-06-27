package com.example.administrator.mapper.weight.card;


public class CardItem {
private String title;
    private String content;
    private String topic;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTopic() {
        return topic;
    }

    public CardItem( String topic,String title, String content) {

        this.title = title;
        this.content = content;
        this.topic = topic;
    }
}
