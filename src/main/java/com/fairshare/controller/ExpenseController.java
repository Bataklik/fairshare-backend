package com.fairshare.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fairshare.model.Expense;
import com.fairshare.service.ExpenseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Operation(summary = "Get all expenses", description = "Return all expenses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "All expenses successfully returned"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @GetMapping()
    public List<Expense> getExpenses(){
        return expenseService.getAllExpenses();
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
}
