package com.manprax.discourseplugin.model.common;

/**
 * Created by Prateek on 29-11-2017.
 */

public class NotificationResponse {
    private Notification[] notifications;
    private int total_rows_notifications;
    private int seen_notification_id;
    private String load_more_notifications;

    public Notification[] getNotifications() {
        return notifications;
    }

    public void setNotifications(Notification[] notifications) {
        this.notifications = notifications;
    }

    public int getTotal_rows_notifications() {
        return total_rows_notifications;
    }

    public void setTotal_rows_notifications(int total_rows_notifications) {
        this.total_rows_notifications = total_rows_notifications;
    }

    public int getSeen_notification_id() {
        return seen_notification_id;
    }

    public void setSeen_notification_id(int seen_notification_id) {
        this.seen_notification_id = seen_notification_id;
    }

    public String getLoad_more_notifications() {
        return load_more_notifications;
    }

    public void setLoad_more_notifications(String load_more_notifications) {
        this.load_more_notifications = load_more_notifications;
    }
}
