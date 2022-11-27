package org.tlbc.hymns.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor
public class ResultValueDTO {
    List<List<String>> taxonomy;
}
