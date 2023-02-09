package com.project.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TodoDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        private String title;

        private int todoOrder;
        @NotNull
        private Boolean completed;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long id;
        private String title;
        private Integer todoOrder;
        private Boolean completed;

        public void setId(long id) {
            this.id = id;
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class TodoResponseDto {
        private long id;
        private String title;
        private int todoOrder;
        private Boolean completed;

    }
}
