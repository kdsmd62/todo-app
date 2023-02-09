package com.project.todoapp.controller;

import com.project.todoapp.dto.TodoDto;
import com.project.todoapp.entity.Todo;
import com.project.todoapp.mapper.TodoMapper;
import com.project.todoapp.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/")
@Validated
@Slf4j
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
        Todo todo = todoService.updateTodo(id, patchDto);

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
    public ResponseEntity deleteToDo(@PathVariable("id") long id) {
        todoService.deleteTodo(id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteToDos() {
        todoService.deleteTodos();

        return ResponseEntity.ok().build();
    }
}
