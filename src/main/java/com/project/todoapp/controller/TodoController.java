package com.project.todoapp.controller;

import com.project.todoapp.dto.TodoDto;
import com.project.todoapp.entity.Todo;
import com.project.todoapp.mapper.TodoMapper;
import com.project.todoapp.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/todos")
@Validated
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper mapper;

    public TodoController(TodoService todoService, TodoMapper mapper) {
        this.todoService = todoService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postToDo(@RequestBody @Valid TodoDto.Post postDto) {
        Todo todo = todoService.createTodo(mapper.postDtoToTodo(postDto));

        return new ResponseEntity(mapper.todoToTodoResponseDto(todo), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchToDo(@Positive @PathVariable("id") long id,
                                    @RequestBody @Valid TodoDto.Patch patchDto) {
        patchDto.setId(id);
        Todo todo = todoService.updateTodo(mapper.patchDtoToTodo(patchDto));

        return new ResponseEntity(mapper.todoToTodoResponseDto(todo), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getToDo(@PathVariable("id") long id) {
        Todo todo = todoService.findTodo(id);

        return new ResponseEntity(mapper.todoToTodoResponseDto(todo), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getToDos() {
        List<Todo> todos = todoService.findTodos();

        return new ResponseEntity(mapper.todosToTodoResponseDtos(todos), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteToDo(@PathVariable("id") long id) {
        todoService.deleteTodo(id);
    }

    @DeleteMapping
    public void deleteToDos() {
        todoService.deleteTodos();
    }
}
