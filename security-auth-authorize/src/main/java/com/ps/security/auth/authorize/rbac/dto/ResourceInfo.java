package com.ps.security.auth.authorize.rbac.dto;

import com.ps.security.auth.authorize.rbac.domain.ResourceType;

import java.util.ArrayList;
import java.util.List;

public class ResourceInfo {
    /**
     *  Resource ID
     */
    private Long id;
    /**
     *
     */
    private Long parentId;
    /**
     * name of the resource
     */
    private String name;
    /**
     * link of resource
     */
    private String link;
    /**
     * icon of the resource
     */
    private String icon;
    /**
     * type of the resource
     */
    private ResourceType type;
    /**
     * Collection of children resources
     */
    private List<ResourceInfo> children = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public List<ResourceInfo> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceInfo> children) {
        this.children = children;
    }
}
