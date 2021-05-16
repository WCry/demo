package com.zxp.model;


import org.springframework.data.jpa.repository.Query;

import javax.persistence.ColumnResult;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;


public interface EmployeeNameOnly {

    String getLastName();
}
