package com.fairshare.ControllerTests;


import com.fairshare.controller.ExpenseController;
import com.fairshare.model.Expense;
import com.fairshare.model.ExpenseCategory;
import com.fairshare.model.ExpenseSplit;
import com.fairshare.model.User;
import com.fairshare.service.ExpenseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExpenseController.class)
public class ExpenseControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ExpenseService expenseServiceMock;

    @Test
    @WithMockUser
    public void getAllExpenses_returns_AllExpenses() throws Exception {
        //? Arrange
        Expense expense_1 = new Expense(
                1L,
                "Dinner",
                20.0,
                ExpenseCategory.FOOD,
                new User(1L, "John Doe", List.of()),
                List.of(),null);
        ExpenseSplit expenseSplit_1 = new ExpenseSplit(1L,
                10.0,
                new User(2L,"Jeremy",List.of()),
                expense_1);
        expense_1.setSplits(List.of(expenseSplit_1));

        Expense expense_2 = new Expense(
                2L,
                "Party",
                8.0,
                ExpenseCategory.FOOD,
                new User(1L, "John Doe", List.of()),
                List.of(),null);
        ExpenseSplit expenseSplit_2 = new ExpenseSplit(1L,
                4.0,
                new User(2L,"Nicolas",List.of()),
                expense_1);
        expense_2.setSplits(List.of(expenseSplit_2));



        when(expenseServiceMock.getAllExpenses())
                .thenReturn(List.of(expense_1,expense_2));

        mockMvc.perform(get("/expenses")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.length()").value(2)))
                .andExpect(jsonPath("$[0].name").value("Dinner"));

    }

    @Test
    @WithMockUser
    public void getExpense_returns_Expense() throws Exception {
        //? Arrange
        Expense expense = new Expense(
                1L,
                "Mcdo food",
                20.0,
                ExpenseCategory.FOOD,
                new User(1L,"TOM", List.of()),
                List.of(),
                null);
        when(expenseServiceMock.getExpenseById(1L))
                .thenReturn(expense);

        mockMvc.perform(get("/expenses/"+1L)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(20.0))
                .andExpect(jsonPath("$.category").value(0))
                .andExpect(jsonPath("$.name").value("Mcdo food"));


    }

}
