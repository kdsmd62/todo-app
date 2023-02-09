package com.project.todoapp.mapper;

import com.project.todoapp.dto.TodoDto;
import com.project.todoapp.entity.Todo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    Todo postDtoToTodo(TodoDto.Post postDto);
    Todo patchDtoToTodo(TodoDto.Patch patchDto);
    TodoDto.TodoResponseDto todoToTodoResponseDto(Todo todo);
    List<TodoDto.TodoResponseDto> todosToTodoResponseDtos(List<Todo> todos);
}
