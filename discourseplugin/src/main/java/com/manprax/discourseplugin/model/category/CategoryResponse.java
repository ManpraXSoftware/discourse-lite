package com.manprax.discourseplugin.model.category;

import java.util.List;

/**
 * Created by Prateek on 23-11-2017.
 */

public class CategoryResponse {
    private CategoryResponse.Item category_list;
    public Item getCategory_list() {
        return category_list;
    }

    public void setCategory_list(Item category_list) {
        this.category_list = category_list;
    }

   public class Item {
        private boolean can_create_category;
        private boolean can_create_topic;
        private boolean draft;
        private String draft_key;
        private int draft_sequence;
        private List<Category> categories;

        public boolean isCan_create_category() {
            return can_create_category;
        }

        public void setCan_create_category(boolean can_create_category) {
            this.can_create_category = can_create_category;
        }

        public boolean isCan_create_topic() {
            return can_create_topic;
        }

        public void setCan_create_topic(boolean can_create_topic) {
            this.can_create_topic = can_create_topic;
        }

        public boolean isDraft() {
            return draft;
        }

        public void setDraft(boolean draft) {
            this.draft = draft;
        }

        public String getDraft_key() {
            return draft_key;
        }

        public void setDraft_key(String draft_key) {
            this.draft_key = draft_key;
        }

        public int getDraft_sequence() {
            return draft_sequence;
        }

        public void setDraft_sequence(int draft_sequence) {
            this.draft_sequence = draft_sequence;
        }

        public List<Category> getCategories() {
            return categories;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }

        @Override
        public String toString() {
            return "CategoryResponse{" +
                    "can_create_category=" + can_create_category +
                    ", can_create_topic=" + can_create_topic +
                    ", draft=" + draft +
                    ", draft_key='" + draft_key + '\'' +
                    ", draft_sequence=" + draft_sequence +
                    ", categories=" + categories +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CategoryResponse{" +
                "category_list=" + category_list +
                '}';
    }
}
