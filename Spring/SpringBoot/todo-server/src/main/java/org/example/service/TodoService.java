package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {

  private final TodoRepository todoRepository;

  public TodoEntity add(TodoRequest request) {
    TodoEntity todoEntity = new TodoEntity(); // tmp Entity를 생성
    todoEntity.setTitle(request.getTitle());  // setTitle
    todoEntity.setOrder(request.getOrder());  // setOrder
    todoEntity.setCompleted(request.getCompleted());  // setCompleted
    return this.todoRepository.save(todoEntity);  // todoRepository에 tmpEntity데이터로 저장
  }

  public TodoEntity searchById(Long id) {
    //findById에 파라미터로 받은 id를 넣고 찾고 만약 값이 없으면 not_found Exception을 날림
    return this.todoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public List<TodoEntity> searchAll() {
    return this.todoRepository.findAll();
  }

  public TodoEntity updateById(Long id, TodoRequest request) {
    TodoEntity todoEntity = this.searchById(id);
    if (request.getTitle() != null){
      todoEntity.setTitle(request.getTitle());
    }
    if (request.getOrder() != null){
      todoEntity.setOrder(request.getOrder());
    }
    if (request.getCompleted() != null){
      todoEntity.setCompleted(request.getCompleted());
    }
    return this.todoRepository.save(todoEntity);
  }

  public void deleteById(Long id) {
    this.todoRepository.deleteById(id);
  }

  public void deleteAll() {
    this.todoRepository.deleteAll();
  }
}
