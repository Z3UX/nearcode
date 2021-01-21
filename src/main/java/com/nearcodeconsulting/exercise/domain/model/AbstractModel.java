package com.nearcodeconsulting.exercise.domain.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * A generic model entity to be used as a base for concrete types of models
 */
@MappedSuperclass
public abstract class AbstractModel implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Short version;

    @CreationTimestamp
    @Column(name = "create_time")
    private Date creationTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @see Model#getId()
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * @see Model#setId(Long)
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the model version
     *
     * @return the model version
     */
    public Short getVersion() {
        return version;
    }

    /**
     * Sets the model version
     *
     * @param version the model version to set
     */
    public void setVersion(Short version) {
        this.version = version;
    }

    /**
     * Gets the model creation time
     *
     * @return the model creation time
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * Sets the model creation time
     *
     * @param creationTime the model creation time to set
     */
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * Gets the model update time
     *
     * @return the model update time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * Sets the model update time
     *
     * @param updateTime the model update time to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
