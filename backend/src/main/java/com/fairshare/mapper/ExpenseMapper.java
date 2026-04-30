package com.fairshare.mapper;

import com.fairshare.dto.ExpenseDTO;
import com.fairshare.model.Expense;

public class ExpenseMapper {


    public static ExpenseDTO toDto(Expense expense){
        return new ExpenseDTO(
                expense.getId(),
                expense.getName(),
                expense.getAmount(),
                expense.getCategory().ordinal(),
                UserMapper.toDto(expense.getPaidBy()),
                expense.getSplits()
                        .stream()
                        .map(ExpenseSplitMapper::toDto)
                        .toList()
        );
    }
}
