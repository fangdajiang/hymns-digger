package org.tlbc.hymns.helper;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PaginationHelperTest {

    @Test
    void testGetPages() {
        assertThat(PaginationHelper.getPages(List.of("value"), 0)).isEqualTo(List.of(List.of("value")));
        assertThat(PaginationHelper.getPages(List.of("value"), 0)).isEqualTo(Collections.emptyList());
    }
}
