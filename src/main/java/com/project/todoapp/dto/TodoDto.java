package com.project.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TodoDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        private String title;

        private Integer order;

        private Boolean completed;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private String title;
        private Integer order;
        private Boolean completed;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TodoResponseDto {
        private Long id;
        private String title;
        private Integer order;
        private Boolean completed;
        private String url;
        public void setUrl(String url) {
            this.url = url + this.id;
        }
    }
}
