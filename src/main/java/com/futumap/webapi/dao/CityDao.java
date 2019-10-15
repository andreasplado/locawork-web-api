package com.futumap.webapi.dao;

import javax.persistence.*;

@Entity
@Table(name = "job_category")
public class CityDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @TableGenerator(
            name="job",
            table="GENERATOR_TABLE",
            pkColumnName = "key",
            valueColumnName = "next",
            pkColumnValue="course",
            allocationSize=30
    )
    private Integer id;


    private String gategoryTitle;
}
