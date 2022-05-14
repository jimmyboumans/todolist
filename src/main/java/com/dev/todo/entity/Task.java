package com.dev.todo.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private boolean done;

    public Task() {
    }

    public Task(String title, String description, boolean done) {
        this.title = title;
        this.description = description;
        this.done = done;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getStatus() {
        return this.done;
    }

    public void setStatus(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(this.id, task.id) && Objects.equals(this.title, task.title) && Objects.equals(this.description, task.description) && Objects.equals(this.done, task.done);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.description, this.done);
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + this.id + ", title='" + this.title + '\'' + ", description='" + this.description + '\'' + ", done='" + this.done + '\'' + '}';
    }

}
