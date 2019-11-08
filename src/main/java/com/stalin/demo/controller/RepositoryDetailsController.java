package com.stalin.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.kohsuke.github.GHRepositorySearchBuilder;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@RestController
public class RepositoryDetailsController {

	@RequestMapping("/")
	public String getRepos() throws IOException {
		GitHub github = new GitHubBuilder().withPassword("stalin.lenin@gmail.com", "Maamangala@12").build();
		GHRepositorySearchBuilder builder = github.searchRepositories();
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/trends")
	public Map<String, String> getTwitterTrends(@RequestParam("placeid") String trendPlace, @RequestParam("count") String trendCount) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		        .setOAuthConsumerKey("HpwdfLFZLON0mT8BLWSSYfTxs")
				.setOAuthConsumerSecret("0FKwKj0GuPMz50dYTYQ3EQxqHfSyNsxipyfPGgXemMHkfWuR3G")
				.setOAuthAccessToken("19703354-wbDGYDsZAIRQKNwPRRQqX3bUkvtLVxoHrGMpkDeCG")
				.setOAuthAccessTokenSecret("2pu202TQ9D1nZUVy77kyN9kfaLtJ6lBxWweW3jddTmVMN");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		Map<String, String> trendDetails = new HashMap<String, String>();
		try {
			Trends trends = twitter.getPlaceTrends(Integer.parseInt(trendPlace));
			int count = 0;
			for (Trend trend : trends.getTrends()) {
				if (count < Integer.parseInt(trendCount)) {
					trendDetails.put(trend.getName(), trend.getURL());
					count++;
				}
			}
		} catch (TwitterException e) {
            trendDetails.put("Error", e.getErrorMessage());
		}catch (Exception e) {
            trendDetails.put("Error", e.getMessage());
		}

		return trendDetails;
	}

}
