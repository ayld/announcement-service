package com.tide.service.impl;

import com.google.common.collect.Sets;
import com.tide.assembler.AnnouncementAssembler;
import com.tide.dao.AnnouncementDao;
import com.tide.domain.Announcement;
import com.tide.dto.AnnouncementDto;
import com.tide.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.Set;

@Component("announcementService")
public class AnnouncementServiceImpl implements AnnouncementService {

    private final static Announcement EMPTY_ANNOUNCMENT = new Announcement(-1l, "", "");

    private AnnouncementAssembler announcementAssembler;
    private AnnouncementDao announcementDao;

    @Autowired
    public AnnouncementServiceImpl(AnnouncementAssembler announcementAssembler, AnnouncementDao announcementDao) {
        this.announcementAssembler = announcementAssembler;
        this.announcementDao = announcementDao;
    }

    @Override
    @Transactional(readOnly = true)
    public AnnouncementDto find(Long id) {
        Assert.notNull(id, "can't find announcement with null id");

        final Optional<Announcement> announcement = announcementDao.findById(id);
        return announcementAssembler.toDto(announcement.orElse(EMPTY_ANNOUNCMENT));
    }

    @Override
    @Transactional(readOnly = true)
    public AnnouncementDto read(Long id) {
        Assert.notNull(id, "can't read announcement with null id");

        final Optional<Announcement> announcement = announcementDao.findById(id);
        if (!announcement.isPresent()) {
            throw new IllegalStateException("Announcement with id: " + id + " not found");
        }
        return announcementAssembler.toDto(announcement.get());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<AnnouncementDto> list() {
        return Sets.newHashSet(announcementAssembler.toDtos(announcementDao.findAll()));
    }

    @Override
    @Transactional
    public AnnouncementDto create(AnnouncementDto announcement) {
        Assert.notNull(announcement, "can't create announcement DTO is null");
        return announcementAssembler.toDto(
                announcementDao.save(announcementAssembler.toEntity(announcement))
        );
    }

    @Override
    @Transactional
    public void like(Long id) {
        Assert.notNull(id, "can't like announcement with null id");
        announcementDao.incrementLikes(id);
    }

    @Override
    @Transactional
    public void dislike(Long id) {
        Assert.notNull(id, "can't dislike announcement with null id");
        announcementDao.incrementDislikes(id);
    }
}
