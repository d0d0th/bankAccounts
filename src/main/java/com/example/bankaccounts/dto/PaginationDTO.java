package com.example.bankaccounts.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaginationDTO {
    Long total_records;
    Integer total_pages;
    Integer current_page;
    Integer next_page;
    Integer prev_page;

    public PaginationDTO(Integer current_page, Integer total_pages, Long total_records){
        this.total_records = total_records;
        this.total_pages = total_pages;
        this.current_page = current_page;
        if(current_page > 1){
            this.prev_page = current_page <= total_pages ?  current_page-1 : total_pages;
        }
        if(current_page < total_pages){
            this.next_page = current_page+1;
        }
    }
}
