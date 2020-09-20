package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "attendances")
@NamedQueries({
    @NamedQuery(
    name = "getAllAttendances",
    query = "SELECT a FROM Attendance AS a ORDER BY a.id DESC"
    ),
    @NamedQuery(
            name = "getAttendancesCount",
            query = "SELECT COUNT(a) FROM Attendance AS a"
            ),
})
@Entity
public class Attendance {
@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

@ManyToOne
@JoinColumn(name = "employee_id", nullable = false)
private Employee employee;

@Column(name = "work_date", nullable = false)
private Date work_date;

@Column(name = "entry", nullable = false)
private Date entry;

@Column(name = "name", nullable = false)
private String name;

@Column(name = "arrive", nullable = false)
private Date arrive;

@Column(name = "finish", nullable = false)
private Date finish;

public Integer getId() {
    return id;
}

public void setId(Integer id) {
    this.id = id;
}

public Employee getEmployee() {
    return employee;
}

public void setEmployee(Employee employee) {
    this.employee = employee;
}

public Date getWork_date() {
    return work_date;
}

public void setWork_date(Date work_date) {
    this.work_date = work_date;
}

public Date getEntry() {
    return entry;
}

public void setEntry(Date entry) {
    this.entry = entry;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public Date getArrive() {
    return arrive;
}

public void setArrive(Date arrive) {
    this.arrive = arrive;
}

public Date getFinish() {
    return finish;
}

public void setFinish(Date finish){
    this.finish = finish;
}
}
