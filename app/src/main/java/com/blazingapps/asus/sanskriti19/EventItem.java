package com.blazingapps.asus.sanskriti19;

class EventItem {

    String event_name;
    String event_id;
    String event_desc;
    String event_url;
    String ticket_url;

    public EventItem(String event_name, String event_id, String event_desc, String event_url, String ticket_url) {
        this.event_name = event_name;
        this.event_id = event_id;
        this.event_desc = event_desc;
        this.event_url = event_url;
        this.ticket_url = ticket_url;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getEvent_desc() {
        return event_desc;
    }

    public void setEvent_desc(String event_desc) {
        this.event_desc = event_desc;
    }

    public String getEvent_url() {
        return event_url;
    }

    public void setEvent_url(String event_url) {
        this.event_url = event_url;
    }

    public String  getTicketUrl() {
        return ticket_url;
    }
}
