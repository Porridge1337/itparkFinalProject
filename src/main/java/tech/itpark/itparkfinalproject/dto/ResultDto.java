package tech.itpark.itparkfinalproject.dto;

import lombok.Data;

@Data
public class ResultDto {

    private boolean success;

    public ResultDto() {
        this.success = true;
    }

}
