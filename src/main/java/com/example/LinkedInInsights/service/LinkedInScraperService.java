package com.example.LinkedInInsights.service;

import com.example.LinkedInInsights.model.Page;
import com.example.LinkedInInsights.repository.PageRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LinkedInScraperService {

    private final PageRepository pageRepository;

    public LinkedInScraperService(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public Page scrapePage(String pageId) throws IOException {
        String url = "https://www.linkedin.com/company/" + pageId;
        Document doc = Jsoup.connect(url).get();

        String name = doc.select("h1").text();
        String profilePicture = doc.select(".profile-image").attr("src");
        String description = doc.select(".company-description").text();
        String followers = doc.select(".followers-count").text().replaceAll("[^0-9]", "");

        Page page = new Page();
        page.setPageId(pageId);
        page.setName(name);
        page.setUrl(url);
        page.setProfilePicture(profilePicture);
        page.setDescription(description);
        page.setFollowers(Integer.parseInt(followers));

        return pageRepository.save(page);
    }
}
