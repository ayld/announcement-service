package com.tide.assembler.impl;

import com.tide.assembler.AnnouncementAssembler;
import com.tide.domain.Announcement;
import com.tide.dto.AnnouncementDto;
import org.springframework.stereotype.Component;

@Component("announcementAssembler")
public class AnnouncementAssemblerImpl extends BaseAssembler<AnnouncementDto, Announcement> implements AnnouncementAssembler{

    @Override
    public AnnouncementDto toDto(Announcement announcement) {
        final AnnouncementDto result = new AnnouncementDto(announcement.getId(), announcement.getTopic(), announcement.getText());
        result.setLikes(announcement.getLikesCount());
        result.setDislikes(announcement.getDislikesCount());
        return result;
    }

    @Override
    public Announcement toEntity(AnnouncementDto announcementDto) {
        final Announcement result = new Announcement(announcementDto.getId(), announcementDto.getTopic(), announcementDto.getText());
        result.setLikesCount(announcementDto.getLikes());
        result.setDislikesCount(announcementDto.getDislikes());
        return result;
    }
}
