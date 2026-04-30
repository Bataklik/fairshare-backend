package com.fairshare.service;

import com.fairshare.model.Group;
import com.fairshare.repository.ExpenseRepository;
import com.fairshare.repository.GroupRepository;
import com.fairshare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BalanceService {

    private final GroupRepository groupRepository;
    private final ExpenseRepository expenseRepository;



    public BalanceService(GroupRepository groupRepository,
                          ExpenseRepository expenseRepository) {
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
    }

    public List<Map<String, Object>> getBalance(long groupId) throws Exception {
        Group group = groupRepository
                .findById(groupId)
                .orElseThrow(() -> new Exception("Group not found"));

       return  List.of();
    }
}
