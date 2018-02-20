package com.tide.domain;

import com.google.common.base.Objects;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
public class Announcement {

    @Id
    @SequenceGenerator(name = "announcement_generator", sequenceName = "announcement_sequence", initialValue = 1)
    @GeneratedValue(generator = "announcement_generator")
    private Long id;

    @Column(nullable = false, updatable = false)
    private String topic;

    @Column(nullable = false)
    private String text;

    @Column
    private Long likesCount = 0l; // long because we'll have a lot of likes

    @Column
    private Long dislikesCount = 0l; // long because we'll have a lot of likes

    public Announcement() {
    }

    public Announcement(Long id, String topic, String text) {
        this.id = id;
        this.topic = topic;
        this.text = text;
    }

    public Announcement(String topic, String text, Long likesCount, Long dislikesCount) {
        this.topic = topic;
        this.text = text;
        this.likesCount = likesCount;
        this.dislikesCount = dislikesCount;
    }

    public Long getDislikesCount() {
        return dislikesCount;
    }

    public void setDislikesCount(Long dislikesCount) {
        this.dislikesCount = dislikesCount;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Long likesCount) {
        this.likesCount = likesCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(topic, that.topic) &&
                Objects.equal(text, that.text) &&
                Objects.equal(likesCount, that.likesCount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, topic, text, likesCount);
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", text='" + text + '\'' +
                ", likesCount=" + likesCount +
                ", dislikesCount=" + dislikesCount +
                '}';
    }
}
