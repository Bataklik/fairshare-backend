package com.fairshare.mapper;

import com.fairshare.dto.ExpenseSplitDTO;
import com.fairshare.model.ExpenseSplit;

public class ExpenseSplitMapper {

    public static ExpenseSplitDTO toDto(ExpenseSplit expenseSplit){
        return new ExpenseSplitDTO(
                UserMapper.toDto(expenseSplit.getUser()),
                expenseSplit.getAmount()
        );
    }
}
