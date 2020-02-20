package com.congdat.notaryweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "news")
public class News extends AbstractModel {
    private String title;
    private String thumbnail;
    private String description;
    private String content;
    private String path;
    Category category;
}
