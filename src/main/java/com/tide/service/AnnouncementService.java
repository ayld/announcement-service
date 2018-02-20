package com.tide.service;


import com.tide.dto.AnnouncementDto;

import java.util.Set;

public interface AnnouncementService {

    AnnouncementDto create(AnnouncementDto announcement);

    AnnouncementDto find(Long id);
    AnnouncementDto read(Long id);

    Set<AnnouncementDto> list();

    void like(Long id);
    void dislike(Long id);
}
