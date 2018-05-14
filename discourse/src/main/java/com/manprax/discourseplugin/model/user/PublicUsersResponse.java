package com.manprax.discourseplugin.model.user;

/**
 * Created by Prateek on 29-11-2017.
 */

public class PublicUsersResponse {
    private DirectoryItem[] directory_items;
    private int total_rows_directory_items;
    private String load_more_directory_items;

    public DirectoryItem[] getDirectory_items() {
        return directory_items;
    }

    public void setDirectory_items(DirectoryItem[] directory_items) {
        this.directory_items = directory_items;
    }

    public int getTotal_rows_directory_items() {
        return total_rows_directory_items;
    }

    public void setTotal_rows_directory_items(int total_rows_directory_items) {
        this.total_rows_directory_items = total_rows_directory_items;
    }

    public String getLoad_more_directory_items() {
        return load_more_directory_items;
    }

    public void setLoad_more_directory_items(String load_more_directory_items) {
        this.load_more_directory_items = load_more_directory_items;
    }
}
