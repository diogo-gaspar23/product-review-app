package com.isep.acme.model.neo4j;

import org.springframework.context.annotation.Profile;

@Profile("neo4j")
public class VoteNeo4j {
    private String vote;
    private Long userID;


    protected VoteNeo4j() {

    }

    public VoteNeo4j(String vote, Long userID) {
        this.vote = vote;
        this.userID = userID;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
