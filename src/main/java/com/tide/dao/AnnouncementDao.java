package com.tide.dao;

import com.tide.domain.Announcement;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AnnouncementDao extends Repository<Announcement, Long> {

    Announcement save(Announcement announcement);

    List<Announcement> findAll();
    Optional<Announcement> findById(Long id);
    Optional<Announcement> findByTopic(String topic);

    @Modifying
    @Query("update Announcement a set a.likesCount = a.likesCount + 1 where a.id = :id")
    void incrementLikes(@Param("id") Long id);

    @Modifying
    @Query("update Announcement a set a.dislikesCount = a.dislikesCount + 1 where a.id = :id")
    void incrementDislikes(@Param("id") Long id);

    void deleteAll();
}
