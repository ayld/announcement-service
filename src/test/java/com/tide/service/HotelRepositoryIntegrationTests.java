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
package com.tide.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelRepositoryIntegrationTests {

//	@Autowired
//	CityRepository cityRepository;
//	@Autowired
//	HotelRepository repository;

	@Test
	public void executesQueryMethodsCorrectly() {
//		City city = this.cityRepository
//				.findAll(new PageRequest(0, 1, Direction.ASC, "name")).getContent().get(0);
//		assertThat(city.getName()).isEqualTo("Atlanta");
//
//		Page<HotelSummary> hotels = this.repository.findByCity(city,
//				new PageRequest(0, 10, Direction.ASC, "name"));
//		Hotel hotel = this.repository.findByCityAndName(city,
//				hotels.getContent().get(0).getName());
//		assertThat(hotel.getName()).isEqualTo("Doubletree");
//
//		List<RatingCount> counts = this.repository.findRatingCounts(hotel);
//		assertThat(counts).hasSize(1);
//		assertThat(counts.get(0).getRating()).isEqualTo(Rating.AVERAGE);
//		assertThat(counts.get(0).getCount()).isGreaterThan(1L);
	}
}
