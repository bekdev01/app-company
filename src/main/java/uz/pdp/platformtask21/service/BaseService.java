package uz.pdp.platformtask21.service;

import uz.pdp.platformtask21.payload.ApiResponse;

import java.util.List;

public interface BaseService<T, R> {
    List<R> getAll();
    R getById(Long id);
    ApiResponse add(T t);
    ApiResponse edit(Long id, T t);
    ApiResponse delete(Long id);
}
