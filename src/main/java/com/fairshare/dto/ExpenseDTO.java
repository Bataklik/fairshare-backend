package com.fairshare.dto;

import java.util.List;

public record ExpenseDTO(long id, String name,
                         double amount, int category,
                         UserDTO paidBy, List<ExpenseSplitDTO> splits ) {
}
