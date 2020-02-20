package com.congdat.notaryweb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
abstract class AbstractModel {

    @Id
    private String id;
    private Date createdDate;
    private String createdBy;
    private Date modifiedDate;
    private String modifiedBy;
}
