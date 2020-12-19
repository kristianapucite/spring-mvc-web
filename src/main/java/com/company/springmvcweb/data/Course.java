package com.company.springmvcweb.data;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "Course")
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "industry")
    @Enumerated(EnumType.STRING)
    private Industry industry;
    @Column(name = "price")
    private int price;
    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private Level level;
    @Column(name = "date_from")
    private LocalDate dateFrom;
    @Column(name = "date_to")
    private LocalDate dateTo;
    @Column(name = "free_slots")
    private int freeSlots;
    @Column(name = "max_slots")
    private int maxSlots;
    @Column(name = "description")
    private String description;



}