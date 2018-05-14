package com.manprax.discourseplugin.model.category;

/**
 * Created by Prateek on 28-11-2017.
 */

public class SingleCategoryResponse {
    private String success;
    private Category category;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "SingleCategoryResponse{" +
                "success='" + success + '\'' +
                ", category=" + category +
                '}';
    }
}
