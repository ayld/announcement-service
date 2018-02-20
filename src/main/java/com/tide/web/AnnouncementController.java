package com.tide.web;

import com.tide.dto.AnnouncementDto;
import com.tide.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller("announcements")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @ResponseBody
    @PutMapping(path = "/announcements", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AnnouncementDto newAnnouncement) {
        announcementService.create(newAnnouncement);
    }

    @ResponseBody
    @PostMapping("/announcements/{id}/like")
    public void like(@PathVariable("id") Long id) {
        announcementService.like(id);
    }

    @ResponseBody
    @PostMapping("/announcements/{id}/dislike")
    public void dislike(@PathVariable("id") Long id) {
        announcementService.dislike(id);
    }

    @ResponseBody
    @GetMapping("/announcements/{id}")
    public AnnouncementDto get(@PathVariable("id") Long id) {
        return announcementService.find(id);
    }

    @ResponseBody
    @GetMapping("/announcements")
    public Set<AnnouncementDto> list() {
        return announcementService.list();
    }
}
