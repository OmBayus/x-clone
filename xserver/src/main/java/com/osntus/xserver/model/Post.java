package com.osntus.xserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue
    public int id;
    public String text;
    public String name;
    public String username;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public LocalDateTime date;
    public boolean isDeleted;
    public int likes;

}
