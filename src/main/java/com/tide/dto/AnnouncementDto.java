package com.tide.dto;

import com.google.common.base.Objects;

public class AnnouncementDto {

    private static final long NOT_YET_PERSISTED_ID = -1;

    private Long id = NOT_YET_PERSISTED_ID;
    private String topic;
    private String text;
    private Long likes = 0l;
    private Long dislikes = 0l;

    public AnnouncementDto() {
    }

    public AnnouncementDto(Long id, String topic, String text) {
        this.id = id;
        this.topic = topic;
        this.text = text;
    }

    public AnnouncementDto(String topic, String text) {
        this.topic = topic;
        this.text = text;
    }

    public String getTopic() {
        return topic;
    }

    public String getText() {
        return text;
    }

    public Long getId() {
        return id;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getDislikes() {
        return dislikes;
    }

    public void setDislikes(Long dislikes) {
        this.dislikes = dislikes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnouncementDto that = (AnnouncementDto) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(topic, that.topic) &&
                Objects.equal(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, topic, text);
    }

    @Override
    public String toString() {
        return "AnnouncementDto{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", text='" + text + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                '}';
    }
}
