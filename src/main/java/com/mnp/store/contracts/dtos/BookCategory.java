package com.mnp.store.contracts.dtos;

import com.github.slugify.Slugify;

public class BookCategory {
    private String slug;
    private String name;

    public BookCategory() {
    }

    public BookCategory(String name) {
        setName(name);
    }

    public String getSlug() {
        return slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

        Slugify slugify = new Slugify();
        this.slug = slugify.slugify(name);
    }
}
