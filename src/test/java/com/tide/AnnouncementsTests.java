/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tide;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tide.dao.AnnouncementDao;
import com.tide.dto.AnnouncementDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = { "spring.jmx.enabled:true", "spring.datasource.jmx-enabled:true" })
@ActiveProfiles("scratch")
public class AnnouncementsTests {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private AnnouncementDao announcementDao;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		this.announcementDao.deleteAll();
	}

	@Test
	public void create() throws Exception {

		final String testAnnouncementTopic = "testTopic";
		final String testAnnouncementText = "Test announcement.";

		final AnnouncementDto testDto = new AnnouncementDto(null, testAnnouncementTopic, testAnnouncementText);

		this.mvc.perform(
				put("/announcements/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(testDto))
		)
		.andExpect(status().isCreated());
	}

	@Test
	public void list() throws Exception{
		// create new announcement

		final String testTopic1 = "Test topic";
		final String testText1 = "Test announcement.";
		final AnnouncementDto testDto1 = new AnnouncementDto(null, testTopic1, testText1);

		this.mvc.perform(
				put("/announcements/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(testDto1))
		)
		.andExpect(status().isCreated());

		// create another announcement
		final String testTopic2 = "Another Test topic";
		final String testText2 = "Another test announcement.";
		final AnnouncementDto testDto2 = new AnnouncementDto(null, testTopic2, testText2);

		this.mvc.perform(
				put("/announcements/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(testDto2))
		)
		.andExpect(status().isCreated());

		// list and check
		final MockHttpServletResponse listResponse = this.mvc.perform(get("/announcements"))
				.andExpect(status().isOk())
				.andReturn().getResponse();

		final Type listType = new TypeToken<ArrayList<AnnouncementDto>>(){}.getType();
		final List<AnnouncementDto> createdAnnouncements = new Gson().fromJson(listResponse.getContentAsString(), listType);

		Assert.assertNotNull(createdAnnouncements);
		Assert.assertEquals(2, createdAnnouncements.size());

		final AnnouncementDto announcement1 = createdAnnouncements
				.stream()
				.filter(
						announcementDto -> announcementDto.getId() == 1
				).findFirst().get();
		Assert.assertEquals(Long.valueOf(1), announcement1.getId());
		Assert.assertEquals(Long.valueOf(0), announcement1.getLikes());
		Assert.assertEquals(Long.valueOf(0), announcement1.getDislikes());
		Assert.assertEquals(testTopic1, announcement1.getTopic());
		Assert.assertEquals(testText1, announcement1.getText());

		final AnnouncementDto announcement2 = createdAnnouncements
				.stream()
				.filter(
						announcementDto -> announcementDto.getId() == 2
				).findFirst().get();
		Assert.assertEquals(Long.valueOf(2), announcement2.getId());
		Assert.assertEquals(Long.valueOf(0), announcement2.getLikes());
		Assert.assertEquals(Long.valueOf(0), announcement2.getDislikes());
		Assert.assertEquals(testTopic2, announcement2.getTopic());
		Assert.assertEquals(testText2, announcement2.getText());
	}

	@Test
	public void createGetLikeDislike() throws Exception {

		// create new announcement
		final String testAnnouncementTopic = "testTopic";
		final String testAnnouncementText = "Test announcement.";

		final AnnouncementDto testDto = new AnnouncementDto(testAnnouncementTopic, testAnnouncementText);

		this.mvc.perform(
				put("/announcements/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(testDto))
		)
		.andExpect(status().isCreated());

		// get created announcement announcement
		MockHttpServletResponse getResponse = this.mvc.perform(get("/announcements/1"))
				.andExpect(status().isOk())
				.andReturn().getResponse();

		AnnouncementDto createdDto = new Gson().fromJson(getResponse.getContentAsString(), AnnouncementDto.class);
		Assert.assertNotNull(createdDto);
		Assert.assertEquals(Long.valueOf(1), createdDto.getId());
		Assert.assertEquals(Long.valueOf(0), createdDto.getLikes());
		Assert.assertEquals(Long.valueOf(0), createdDto.getDislikes());
		Assert.assertEquals(testAnnouncementTopic, createdDto.getTopic());
		Assert.assertEquals(testAnnouncementText, createdDto.getText());

		// like new announcement
		this.mvc.perform(post("/announcements/1/like")).andExpect(status().isOk());

		// dislike new announcement twice
		this.mvc.perform(post("/announcements/1/dislike")).andExpect(status().isOk());
		this.mvc.perform(post("/announcements/1/dislike")).andExpect(status().isOk());

		// get again and check likes/dislikes count
		getResponse = this.mvc.perform(get("/announcements/1"))
				.andExpect(status().isOk())
				.andReturn().getResponse();

		createdDto = new Gson().fromJson(getResponse.getContentAsString(), AnnouncementDto.class);
		Assert.assertNotNull(createdDto);
		Assert.assertEquals(Long.valueOf(1), createdDto.getId());
		Assert.assertEquals(Long.valueOf(1), createdDto.getLikes());
		Assert.assertEquals(Long.valueOf(2), createdDto.getDislikes());
		Assert.assertEquals(testAnnouncementTopic, createdDto.getTopic());
		Assert.assertEquals(testAnnouncementText, createdDto.getText());

	}
}
