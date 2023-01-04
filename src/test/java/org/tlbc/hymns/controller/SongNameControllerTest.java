package org.tlbc.hymns.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.tlbc.hymns.model.ElasticSearchSong;
import org.tlbc.hymns.model.HymnEntity;
import org.tlbc.hymns.model.SongSummary;
import org.tlbc.hymns.service.ElasticSearchSongService;
import org.tlbc.hymns.service.HymnService;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SongNameController.class)
class SongNameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ElasticSearchSongService mockElasticSearchSongService;
    @MockBean
    private HymnService mockHymnService;

    @Test
    void testGetHymns() throws Exception {
        // Setup
        // Configure HymnService.getHymns(...).
        final HymnEntity hymnEntity = new HymnEntity();
        hymnEntity.setId(0);
        hymnEntity.setLabelSet("labelSet");
        hymnEntity.setNameCn("nameCn");
        hymnEntity.setNameEn("nameEn");
        hymnEntity.setBookName("bookName");
        hymnEntity.setVerse("verse");
        final List<HymnEntity> hymnEntities = List.of(hymnEntity);
        when(mockHymnService.getHymns()).thenReturn(hymnEntities);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/songs/hymns")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetHymns_HymnServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockHymnService.getHymns()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/songs/hymns")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetSummary() throws Exception {
        // Setup
        when(mockElasticSearchSongService.getSummary()).thenReturn(new SongSummary(0, 0));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/songs/summary")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testSearchSongs() throws Exception {
        // Setup
        // Configure ElasticSearchSongService.findByLabels(...).
        final List<ElasticSearchSong> elasticSearchSongs = List.of(
                new ElasticSearchSong(0, "category1", "category2", "nameCn", "nameEn"));
        when(mockElasticSearchSongService.findByLabels(Set.of("value"))).thenReturn(elasticSearchSongs);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/songs")
                        .param("label", "label")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testSearchSongs_ElasticSearchSongServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockElasticSearchSongService.findByLabels(Set.of("value"))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/songs")
                        .param("label", "label")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetSong() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/songs/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
