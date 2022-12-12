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
import org.tlbc.hymns.model.CategoryLabel;
import org.tlbc.hymns.service.CategoryLabelService;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryLabelController.class)
class CategoryLabelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryLabelService mockCategoryLabelService;

    @Test
    void testGetCategoryLabels() throws Exception {
        // Setup
        when(mockCategoryLabelService.getLoadedCategoryLabels()).thenReturn(List.of(new CategoryLabel(0, "c", "l")));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/labels")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetCategoryLabels_CategoryLabelServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockCategoryLabelService.getLoadedCategoryLabels()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/labels")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetCategories() throws Exception {
        // Setup
        when(mockCategoryLabelService.getCategories()).thenReturn(Set.of("value"));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/labels/categories")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetCategories_CategoryLabelServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockCategoryLabelService.getCategories()).thenReturn(Collections.emptySet());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/labels/categories")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }
}
