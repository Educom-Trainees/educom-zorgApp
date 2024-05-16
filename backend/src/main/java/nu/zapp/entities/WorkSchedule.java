package nu.zapp.entities;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class WorkSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(length=9)
    private String day;

    @Column()
    private LocalTime start_shift;

    @Column()
    private LocalTime end_shift;

}
