package com.congdat.notaryweb.dto;

import lombok.Data;

import java.util.Date;

@Data
public abstract class AbstractDTO {

    private String id;
    private Date createdDate;
    private String createdBy;
    private Date modifiedDate;
    private String modifiedBy;

}
