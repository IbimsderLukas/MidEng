package model;

import java.util.ArrayList;
import java.util.List;

public class VoteData {

    private String regionID;
    private String regionName;
    private String regionAddress;
    private String regionPostalCode;
    private String federalState;
    private String timestamp;
    private List<PartyData> countingData;
    private List<PreferentialVote> preferentialVotes; // Vorzugsstimmen

    /**
     * Constructor
     */
    public VoteData() {
        this.countingData = new ArrayList<>();
        this.preferentialVotes = new ArrayList<>();
    }

    /**
     * Setter and Getter Methods
     */
    public String getRegionID() {
        return regionID;
    }

    public void setRegionID(String regionID) {
        this.regionID = regionID;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionAddress() {
        return regionAddress;
    }

    public void setRegionAddress(String regionAddress) {
        this.regionAddress = regionAddress;
    }

    public String getRegionPostalCode() {
        return regionPostalCode;
    }

    public void setRegionPostalCode(String regionPostalCode) {
        this.regionPostalCode = regionPostalCode;
    }

    public String getFederalState() {
        return federalState;
    }

    public void setFederalState(String federalState) {
        this.federalState = federalState;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<PartyData> getCountingData() {
        return countingData;
    }

    public void setCountingData(List<PartyData> countingData) {
        this.countingData = countingData;
    }

    public List<PreferentialVote> getPreferentialVotes() {
        return preferentialVotes;
    }

    public void setPreferentialVotes(List<PreferentialVote> preferentialVotes) {
        this.preferentialVotes = preferentialVotes;
    }

    /**
     * Converts the object to XML format
     */
    public String toXML() {
        StringBuilder xml = new StringBuilder();
        xml.append("<electionData>\n");
        xml.append(String.format("  <regionID>%s</regionID>\n", regionID));
        xml.append(String.format("  <regionName>%s</regionName>\n", regionName));
        xml.append(String.format("  <regionAddress>%s</regionAddress>\n", regionAddress));
        xml.append(String.format("  <regionPostalCode>%s</regionPostalCode>\n", regionPostalCode));
        xml.append(String.format("  <federalState>%s</federalState>\n", federalState));
        xml.append(String.format("  <timestamp>%s</timestamp>\n", timestamp));

        // Counting data (Parteiergebnisse)
        xml.append("  <countingData>\n");
        for (PartyData party : countingData) {
            xml.append(party.toXML());
        }
        xml.append("  </countingData>\n");

        // Vorzugsstimmen (Preferential Votes)
        xml.append("  <preferentialVotes>\n");
        for (PreferentialVote vote : preferentialVotes) {
            xml.append(vote.toXML());
        }
        xml.append("  </preferentialVotes>\n");

        xml.append("</electionData>");
        return xml.toString();
    }

    /**
     * Converts the object to JSON format
     */
    public String toJSON() {
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append(String.format("  \"regionID\": \"%s\",\n", regionID));
        json.append(String.format("  \"regionName\": \"%s\",\n", regionName));
        json.append(String.format("  \"regionAddress\": \"%s\",\n", regionAddress));
        json.append(String.format("  \"regionPostalCode\": \"%s\",\n", regionPostalCode));
        json.append(String.format("  \"federalState\": \"%s\",\n", federalState));
        json.append(String.format("  \"timestamp\": \"%s\",\n", timestamp));

        // Counting data (Parteiergebnisse)
        json.append("  \"countingData\": [\n");
        for (int i = 0; i < countingData.size(); i++) {
            json.append(countingData.get(i).toJSON());
            if (i < countingData.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("  ],\n");

        // Preferential Votes
        json.append("  \"preferentialVotes\": [\n");
        for (int i = 0; i < preferentialVotes.size(); i++) {
            json.append(preferentialVotes.get(i).toJSON());
            if (i < preferentialVotes.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("  ]\n");
        json.append("}");
        return json.toString();
    }

    /**
     * Inner Class for Party Data
     */
    public static class PartyData {
        private String partyID;
        private int amountVotes;

        public String getPartyID() {
            return partyID;
        }

        public void setPartyID(String partyID) {
            this.partyID = partyID;
        }

        public int getAmountVotes() {
            return amountVotes;
        }

        public void setAmountVotes(int amountVotes) {
            this.amountVotes = amountVotes;
        }

        public String toXML() {
            return String.format("    <party>\n      <partyID>%s</partyID>\n      <amountVotes>%d</amountVotes>\n    </party>\n", partyID, amountVotes);
        }

        public String toJSON() {
            return String.format("    {\n      \"partyID\": \"%s\",\n      \"amountVotes\": %d\n    }", partyID, amountVotes);
        }
    }

    /**
     * Inner Class for Preferential Votes
     */
    public static class PreferentialVote {
        private int listNumber;
        private String personName;
        private int votes;

        public int getListNumber() {
            return listNumber;
        }

        public void setListNumber(int listNumber) {
            this.listNumber = listNumber;
        }

        public String getPersonName() {
            return personName;
        }

        public void setPersonName(String personName) {
            this.personName = personName;
        }

        public int getVotes() {
            return votes;
        }

        public void setVotes(int votes) {
            this.votes = votes;
        }

        public String toXML() {
            return String.format("    <preferentialVote>\n      <listNumber>%d</listNumber>\n      <personName>%s</personName>\n      <votes>%d</votes>\n    </preferentialVote>\n", listNumber, personName, votes);
        }

        public String toJSON() {
            return String.format("    {\n      \"listNumber\": %d,\n      \"personName\": \"%s\",\n      \"votes\": %d\n    }", listNumber, personName, votes);
        }
    }
}