package com.project.todoapp.service;

import com.project.todoapp.entity.Todo;
import com.project.todoapp.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Todo todo) {
        // 수정할 todoItem 정보 조회
        Todo findTodoItem = findVerifiedTodoItem(todo.getId());

        // 값이 null 인지 확인
        Optional.ofNullable(todo.getTitle())
                .ifPresent(title -> findTodoItem.setTitle(title));
        Optional.ofNullable(todo.getTodoOrder())
                .ifPresent(todoOrder -> findTodoItem.setTodoOrder(todoOrder));
        Optional.ofNullable(todo.getCompleted())
                .ifPresent(completed -> findTodoItem.setCompleted(completed));

        return todoRepository.save(findTodoItem);
    }

    public Todo findTodo(long id) {
        // 매개변수 id 값이 존재하는지 확인
        return findVerifiedTodoItem(id);
    }

    public List<Todo> findTodos() {
        return todoRepository.findAll();
    }

    public void deleteTodo(long id) {
        todoRepository.deleteById(id);
    }

    public void deleteTodos() {
        todoRepository.deleteAll();
    }

    private Todo findVerifiedTodoItem(long id) {
        // 매개변수 id 값이 존재하는지 확인하는 메서드
        return todoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException());
    }
}
