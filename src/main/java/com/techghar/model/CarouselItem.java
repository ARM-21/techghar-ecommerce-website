package com.techghar.model;

/**
 * CarouselItem represents a single carousel slide displayed on the homepage.
 * Each item contains a title, description, and image URL.
 */
public class CarouselItem {
    private int id;
    private String title;
    private String description;
    private String imageUrl;

    /**
     * Constructor for creating a new carousel item without an ID.
     *
     * @param title       The title of the carousel item
     * @param description The description text for the carousel item
     * @param imageUrl    The URL path to the carousel item image
     */
    public CarouselItem(String title, String description, String imageUrl) {
        super();
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }
    
    /**
     * Constructor for creating a carousel item with an existing ID.
     *
     * @param id          The unique identifier for the carousel item
     * @param title       The title of the carousel item
     * @param description The description text for the carousel item
     * @param imageUrl    The URL path to the carousel item image
     */
    public CarouselItem(int id, String title, String description, String imageUrl) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }
    
    /**
     * Gets the unique identifier of the carousel item.
     *
     * @return The carousel item ID
     */
    public int getId() { return id; }
    
    /**
     * Sets the unique identifier of the carousel item.
     *
     * @param id The carousel item ID to set
     */
    public void setId(int id) { this.id = id; }
    
    /**
     * Gets the title of the carousel item.
     *
     * @return The carousel item title
     */
    public String getTitle() { return title; }
    
    /**
     * Sets the title of the carousel item.
     *
     * @param title The carousel item title to set
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * Gets the description of the carousel item.
     *
     * @return The carousel item description
     */
    public String getDescription() { return description; }
    
    /**
     * Sets the description of the carousel item.
     *
     * @param description The carousel item description to set
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Gets the image URL of the carousel item.
     *
     * @return The carousel item image URL
     */
    public String getImageUrl() { return imageUrl; }
    
    /**
     * Sets the image URL of the carousel item.
     *
     * @param imageUrl The carousel item image URL to set
     */
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
