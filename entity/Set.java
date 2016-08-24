package entity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table SET.
 */
public class Set {

    private Long id;
    private String name;
    private Integer slots;
    private String description;

    public Set() {
    }

    public Set(Long id) {
        this.id = id;
    }

    public Set(Long id, String name, Integer slots, String description) {
        this.id = id;
        this.name = name;
        this.slots = slots;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSlots() {
        return slots;
    }

    public void setSlots(Integer slots) {
        this.slots = slots;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}