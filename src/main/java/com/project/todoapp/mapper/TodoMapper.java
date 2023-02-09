package com.project.todoapp.mapper;

import com.project.todoapp.dto.TodoDto;
import com.project.todoapp.entity.Todo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    Todo postDtoToTodo(TodoDto.Post postDto);
    default TodoDto.TodoResponseDto todoToTodoResponseDto(Todo todo) {
        TodoDto.TodoResponseDto responseDto = new TodoDto.TodoResponseDto();
        responseDto.setId(todo.getId());
        responseDto.setTitle(todo.getTitle());
        responseDto.setOrder(todo.getOrder());
        responseDto.setCompleted(todo.getCompleted());
        responseDto.setUrl("http://localhost:8080/");

        return responseDto;
    }
    List<TodoDto.TodoResponseDto> todosToTodoResponseDtos(List<Todo> todos);
}
