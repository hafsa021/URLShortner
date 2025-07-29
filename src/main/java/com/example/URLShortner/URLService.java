package com.example.URLShortner;

import com.example.URLShortner.URLMapping;
import com.example.URLShortner.URLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class URLService {

    @Autowired
    private URLRepository repository;

    private final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public URLMapping shortenUrl(String longUrl) {
        URLMapping urlMapping = new URLMapping();
        urlMapping.setOriginalUrl(longUrl);
        urlMapping.setClickCount(0);

        URLMapping saved = repository.save(urlMapping);
        String shortCode = encodeBase62(saved.getId());
        saved.setShortCode(shortCode);

        return repository.save(saved);
    }

    public String getOriginalUrl(String shortCode) {
        URLMapping mapping = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Short URL not found"));

        mapping.setClickCount(mapping.getClickCount() + 1);
        repository.save(mapping);

        return mapping.getOriginalUrl();
    }

    private String encodeBase62(Long id) {
        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            sb.append(BASE62.charAt((int) (id % 62)));
            id /= 62;
        }
        return sb.reverse().toString();
    }
}
