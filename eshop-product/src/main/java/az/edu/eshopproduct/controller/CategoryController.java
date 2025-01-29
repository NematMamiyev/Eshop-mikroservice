package az.edu.eshopproduct.controller;


import az.edu.eshopproduct.dto.request.ReqCategory;
import az.edu.eshopproduct.dto.response.RespCategory;
import az.edu.eshopproduct.dto.response.RespStatus;
import az.edu.eshopproduct.dto.response.Response;
import az.edu.eshopproduct.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.CREATED)
    //@PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')or hasAuthority('OPERATOR')")
    @PostMapping
    public Response<RespCategory> addCategory(@RequestBody @Valid ReqCategory reqCategory) {
        return categoryService.addCategory(reqCategory);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Response<List<RespCategory>> categoryList() {
        return categoryService.categoryList();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response<RespCategory> getCategoryById(@PathVariable @NotNull(message = "Id is required") Long id) {
        return categoryService.getCategoryById(id);
    }

    @ResponseStatus(HttpStatus.OK)
   // @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public Response<RespCategory> updateCategory(@PathVariable @NotNull(message = "Id is required") Long id, @RequestBody @Valid ReqCategory reqCategory) {
        return categoryService.updateCategory(id, reqCategory);
    }

    @ResponseStatus(HttpStatus.OK)
    //@PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public RespStatus deleteCategory(@PathVariable @NotNull(message = "Id is required") Long id) {
        return categoryService.deleteCategory(id);
    }
}
