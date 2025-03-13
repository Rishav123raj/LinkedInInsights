package com.example.LinkedInInsights.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pageId;
    private String name;
    private String url;
    private String profilePicture;
    private String description;
    private String website;
    private String industry;
    private int followers;
    private int headCount;
    private String specialities;

    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;
}
