package com.fairshare.controller;

import java.util.List;

import com.fairshare.dto.ExpenseDTO;
import com.fairshare.dto.UserDTO;
import com.fairshare.mapper.ExpenseMapper;
import com.fairshare.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fairshare.model.Expense;
import com.fairshare.service.ExpenseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ExpenseMapper expenseMapper = new ExpenseMapper();

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Operation(summary = "Get all expenses", description = "Return all expenses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "All expenses successfully returned"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @GetMapping()
    public List<ExpenseDTO> getExpenses(){
        return expenseService.getAllExpenses()
                .stream()
                .map(ExpenseMapper::toDto)
                .toList();
    }

    @Operation(summary = "Create an expense", description = "Return a expense")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Expense successfully created"), // 201 is de standaard voor 'Created'
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Expense createExpense(@RequestBody Expense expense){
        return expenseService.createExpense(expense);
    }

    @Operation(summary = "Get a expense by id", description = "Return a expense by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expense found and returned"),
            @ApiResponse(responseCode = "404", description = "Expense not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getUser(@PathVariable Long id) throws Exception {
        var foundExpense = expenseService.getExpenseById(id);
        return ResponseEntity.ok(ExpenseMapper.toDto(foundExpense));
    }
}
