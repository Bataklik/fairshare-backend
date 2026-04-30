package com.fairshare.dto;

import java.util.List;

public record GroupDTO(long id, String name, List<String> users) {
}
