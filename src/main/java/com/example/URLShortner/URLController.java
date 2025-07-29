package com.example.URLShortner;

import com.example.URLShortner.URLMapping;
import com.example.URLShortner.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class URLController {

    @Autowired
    private URLService urlService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/shorten")
    public String shorten(@RequestParam("url") String longUrl, Model model) {
        URLMapping result = urlService.shortenUrl(longUrl);
        String shortUrl = "http://localhost:8080/" + result.getShortCode();
        model.addAttribute("shortUrl", shortUrl);
        return "index";
    }

    @GetMapping("/{shortCode}")
    public RedirectView redirect(@PathVariable String shortCode) {
        String originalUrl = urlService.getOriginalUrl(shortCode);
        return new RedirectView(originalUrl);
    }
}
