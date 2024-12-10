package org.example;
import model.VoteData;

import java.util.Arrays;

public class VoteService {
    public static VoteData getVoteData(String regionID) {
        VoteData data = new VoteData();
        data.setRegionID(regionID);
        data.setRegionName("Linz Bahnhof");
        data.setRegionAddress("Bahnhofsstrasse 27/9");
        data.setRegionPostalCode("4020");
        data.setFederalState("Austria");
        data.setTimestamp("2024-09-12 11:48:21");

        // Simulierte Parteien und Stimmen
        VoteData.PartyData party1 = new VoteData.PartyData();
        party1.setPartyID("OEVP");
        party1.setAmountVotes(322);

        VoteData.PartyData party2 = new VoteData.PartyData();
        party2.setPartyID("SPOE");
        party2.setAmountVotes(301);

        VoteData.PartyData party3 = new VoteData.PartyData();
        party3.setPartyID("FPOE");
        party3.setAmountVotes(231);

        VoteData.PartyData party4 = new VoteData.PartyData();
        party4.setPartyID("GRUENE");
        party4.setAmountVotes(211);

        VoteData.PartyData party5 = new VoteData.PartyData();
        party5.setPartyID("NEOS");
        party5.setAmountVotes(182);

        // Parteien hinzufügen
        data.setCountingData(Arrays.asList(party1, party2, party3, party4, party5));

        // Simulierte Vorzugsstimmen hinzufügen
        VoteData.PreferentialVote candidate1 = new VoteData.PreferentialVote();
        candidate1.setListNumber(1);
        candidate1.setPersonName("Max Mustermann ÖVP");
        candidate1.setVotes(75);

        VoteData.PreferentialVote candidate2 = new VoteData.PreferentialVote();
        candidate2.setListNumber(2);
        candidate2.setPersonName("Maria Musterfrau ÖVP");
        candidate2.setVotes(63);

        VoteData.PreferentialVote candidate3 = new VoteData.PreferentialVote();
        candidate3.setListNumber(3);
        candidate3.setPersonName("Franz Huber SPÖ");
        candidate3.setVotes(58);

        VoteData.PreferentialVote candidate4 = new VoteData.PreferentialVote();
        candidate4.setListNumber(4);
        candidate4.setPersonName("Anna Schuster FPÖ");
        candidate4.setVotes(49);

        // Vorzugsstimmen hinzufügen
        data.setPreferentialVotes(Arrays.asList(candidate1, candidate2, candidate3, candidate4));

        return data;
    }
}
