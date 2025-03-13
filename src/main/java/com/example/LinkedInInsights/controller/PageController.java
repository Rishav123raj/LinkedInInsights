package com.example.LinkedInInsights.controller;

import com.example.LinkedInInsights.model.Page;
import com.example.LinkedInInsights.service.LinkedInScraperService;
import com.example.LinkedInInsights.service.OpenAiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/pages")
public class PageController {

    private final LinkedInScraperService scraperService;
    private final OpenAiService openAiService;

    public PageController(LinkedInScraperService scraperService, OpenAiService openAiService) {
        this.scraperService = scraperService;
        this.openAiService = openAiService;
    }

    /**
     * Fetches LinkedIn Page details by Page ID.
     * Scrapes if not found in DB.
     */
    @GetMapping("/{pageId}")
    public ResponseEntity<Page> getPage(@PathVariable String pageId) throws IOException {
        Page page = scraperService.scrapePage(pageId);
        return ResponseEntity.ok(page);
    }

    /**
     * Filters pages by follower count, name, or industry.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Page>> searchPages(
            @RequestParam(required = false) Integer minFollowers,
            @RequestParam(required = false) Integer maxFollowers,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String industry) {

        List<Page> pages = scraperService.searchPages(minFollowers, maxFollowers, name, industry);
        return ResponseEntity.ok(pages);
    }

    /**
     * Fetches an AI-generated summary for a given LinkedIn page.
     */
    @GetMapping("/{pageId}/summary")
    public ResponseEntity<String> getPageSummary(@PathVariable String pageId) {
        Page page = scraperService.getPageFromDb(pageId);
        if (page == null) {
            return ResponseEntity.notFound().build();
        }
        String summary = openAiService.generateSummary(page.getDescription());
        return ResponseEntity.ok(summary);
    }
}
